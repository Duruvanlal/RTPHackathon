
package com.swapstech.hackathon.common.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.swapstech.hackathon.common.model.CurrencyAmount;
import com.swapstech.hackathon.common.model.RfpResponse;
import com.swapstech.hackathon.common.model.RtpRfpDTO;
import com.swapstech.hackathon.common.model.TokenResponseDTO;
import com.swapstech.hackathon.common.model.User;
import com.swapstech.hackathon.common.model.UserPaymentAccount;
import com.swapstech.hackathon.common.model.ValueObject;
import com.swapstech.hackathon.common.model.ZillTransaction;
import com.swapstech.hackathon.common.model.ZillTransactionDetails;
import com.swapstech.hackathon.common.repository.util.ApiUrlConstants;

/**
 * @author Duruvanlal TJ
 *
 */
@Service
public class OracleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OracleService.class);
	
	@Autowired
	UserAccountService userAcctService;
	@Autowired
	ZillTransactionDetailsService transDetailsService;

	public static final String AUTH = "Authorization";
	public static final String BASIC = "Basic MmQ3OWU5MzllMDQyYXBpYWNjZXNzOGU1ZmFiNDM2ZmI1NTgxOndlbGNvbWUx";
	public static final String BEARER = "Bearer ";

	public String getToken(String userId, String password) {
		String url = ApiUrlConstants.TOKEN_URL;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add(AUTH, BASIC);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("grant_type", "PASSWORD");
		map.add("username", userId);
		map.add("password", password);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<TokenResponseDTO> response = restTemplate.postForEntity(url, request, TokenResponseDTO.class);
		LOGGER.info("Access Token for:::{}", response.getBody().getAccess_token());
		return response.getBody().getAccess_token();
	}

	public RtpRfpDTO makeRfpRequest(ZillTransaction transaction) {
		String url = ApiUrlConstants.RFP_URL;
		String userId = null;
		String pwd = null;

		RtpRfpDTO rfpDto = new RtpRfpDTO();
		ValueObject creditAccount = new ValueObject();
		UserPaymentAccount requestorAccount = userAcctService.findUserAccountByUpa(transaction.getRequestorUpaCd());
		if (requestorAccount != null && StringUtils.isNotBlank(requestorAccount.getAccountNumber())) {
			creditAccount.setValue(requestorAccount.getAccountNumber());
			User creditor = userAcctService.findByUserId(requestorAccount.getUserId());
			if (creditor != null) {
				rfpDto.setCreditorName(creditor.getUserName());
				rfpDto.setAgentMemId(creditor.getAgentMemberId());
				userId = creditor.getUserId();
				pwd = creditor.getPassword();
			}
		}
		rfpDto.setCreditAccountId(creditAccount);
		UserPaymentAccount approverAccount = userAcctService.findUserAccountByUpa(transaction.getPayerUpaCd());
		if (approverAccount != null && StringUtils.isNotBlank(approverAccount.getAccountNumber())) {
			rfpDto.setDebitAccountId(approverAccount.getAccountNumber());
			User payer = userAcctService.findByUserId(approverAccount.getUserId());
			if (payer != null) {
				rfpDto.setDebtorName(payer.getUserName());
			}
		}
		//ZillTransactionDetails transDetails = transDetailsService.getZillTransactionDetailByTransCd(transaction.getPaymentTransCode());
		CurrencyAmount currencyAmount = new CurrencyAmount();
		//if (transDetails != null && transDetails.getPaymentAmount() != null) {
			currencyAmount.setCurrency("USD");
			currencyAmount.setAmount(transaction.getPaymentAmount());
			rfpDto.setAmount(currencyAmount);
		//}
		String token = getToken(userId, pwd);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(AUTH, BEARER + token);
		LOGGER.info("RtpRfp Request:::{}", rfpDto);
		HttpEntity<RtpRfpDTO> request = new HttpEntity<RtpRfpDTO>(rfpDto, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<RfpResponse> response = restTemplate.postForEntity(url, request, RfpResponse.class);
		RtpRfpDTO rtpRfpDTO = null;
		if (response != null && response.getBody() != null) {
			if (StringUtils.isNotBlank(response.getBody().getStatus().getResult())
					&& "SUCCESSFUL".equalsIgnoreCase(response.getBody().getStatus().getResult())) {
				rtpRfpDTO = response.getBody().getUsDomesticPayinInstructionDTO();
			}
		}
		LOGGER.info("RtpRfp Response:::{}", rtpRfpDTO);
		return rtpRfpDTO;
	}

	public RtpRfpDTO approveRfp(ZillTransaction transaction) {
		String url = ApiUrlConstants.RFP_ACTION_URL + transaction.getInstructionId() + "/accept";
		String userId = null;
		String pwd = null;

		RtpRfpDTO rfpDto = new RtpRfpDTO();
		ValueObject creditAccount = new ValueObject();
		UserPaymentAccount requestorAccount = userAcctService.findUserAccountByUpa(transaction.getRequestorUpaCd());
		if (requestorAccount != null && StringUtils.isNotBlank(requestorAccount.getAccountNumber())) {
			creditAccount.setValue(requestorAccount.getAccountNumber());
			User creditor = userAcctService.findByUserId(requestorAccount.getUserId());
			if (creditor != null) {
				rfpDto.setCreditorName(creditor.getUserName());

			}
		}
		rfpDto.setCreditAccountId(creditAccount);
		UserPaymentAccount approverAccount = userAcctService.findUserAccountByUpa(transaction.getPayerUpaCd());
		if (approverAccount != null && StringUtils.isNotBlank(approverAccount.getAccountNumber())) {
			rfpDto.setDebitAccountId(approverAccount.getAccountNumber());
			User payer = userAcctService.findByUserId(approverAccount.getUserId());
			if (payer != null) {
				rfpDto.setDebtorName(payer.getUserName());
				rfpDto.setAgentMemId(payer.getAgentMemberId());
				userId = payer.getUserId();
				pwd = payer.getPassword();
			}
		}
		ZillTransactionDetails transDetails = transDetailsService
				.getZillTransactionDetailByTransCd(transaction.getPaymentTransCode());
		CurrencyAmount currencyAmount = new CurrencyAmount();
		if (transDetails != null && transDetails.getPaymentAmount() != null) {
			currencyAmount.setCurrency(transDetails.getPaymentCurrency());
			currencyAmount.setAmount(transDetails.getPaymentAmount());
			rfpDto.setAmount(currencyAmount);
		}
		String token = getToken(userId, pwd);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(AUTH, BEARER + token);
		LOGGER.info("RtpRfp Approve Request:::{}", rfpDto);
		HttpEntity<RtpRfpDTO> request = new HttpEntity<RtpRfpDTO>(rfpDto, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<RfpResponse> response = restTemplate.postForEntity(url, request, RfpResponse.class);
		RtpRfpDTO rtpRfpDTO = null;
		if (response != null && response.getBody() != null) {
			if (StringUtils.isNotBlank(response.getBody().getStatus().getResult())
					&& "SUCCESSFUL".equalsIgnoreCase(response.getBody().getStatus().getResult())) {
				rtpRfpDTO = response.getBody().getUsDomesticPayinInstructionDTO();
			}
		}
		LOGGER.info("RtpRfp Approve Response:::{}", rtpRfpDTO);
		return rtpRfpDTO;
	}

	public RtpRfpDTO rejectRfp(ZillTransaction transaction) {
		String url = ApiUrlConstants.RFP_ACTION_URL + transaction.getInstructionId() + "/reject";
		String userId = null;
		String pwd = null;

		RtpRfpDTO rfpDto = new RtpRfpDTO();
		ValueObject creditAccount = new ValueObject();
		UserPaymentAccount requestorAccount = userAcctService.findUserAccountByUpa(transaction.getRequestorUpaCd());
		if (requestorAccount != null && StringUtils.isNotBlank(requestorAccount.getAccountNumber())) {
			creditAccount.setValue(requestorAccount.getAccountNumber());
			User creditor = userAcctService.findByUserId(requestorAccount.getUserId());
			if (creditor != null) {
				rfpDto.setCreditorName(creditor.getUserName());
				rfpDto.setAgentMemId(creditor.getAgentMemberId());
				userId = creditor.getUserId();
				pwd = creditor.getPassword();
			}
		}
		rfpDto.setCreditAccountId(creditAccount);
		UserPaymentAccount approverAccount = userAcctService.findUserAccountByUpa(transaction.getPayerUpaCd());
		if (approverAccount != null && StringUtils.isNotBlank(approverAccount.getAccountNumber())) {
			rfpDto.setDebitAccountId(approverAccount.getAccountNumber());
			User payer = userAcctService.findByUserId(approverAccount.getUserId());
			if (payer != null) {
				rfpDto.setDebtorName(payer.getUserName());
			}
		}
		ZillTransactionDetails transDetails = transDetailsService
				.getZillTransactionDetailByTransCd(transaction.getPaymentTransCode());
		CurrencyAmount currencyAmount = new CurrencyAmount();
		if (transDetails != null && transDetails.getPaymentAmount() != null) {
			currencyAmount.setCurrency(transDetails.getPaymentCurrency());
			currencyAmount.setAmount(transDetails.getPaymentAmount());
			rfpDto.setAmount(currencyAmount);
		}
		String token = getToken(userId, pwd);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(AUTH, BEARER + token);
		LOGGER.info("RtpRfp Reject Request:::{}", rfpDto);
		HttpEntity<RtpRfpDTO> request = new HttpEntity<RtpRfpDTO>(rfpDto, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<RfpResponse> response = restTemplate.postForEntity(url, request, RfpResponse.class);
		RtpRfpDTO rtpRfpDTO = null;
		if (response != null && response.getBody() != null) {
			if (StringUtils.isNotBlank(response.getBody().getStatus().getResult())
					&& "SUCCESSFUL".equalsIgnoreCase(response.getBody().getStatus().getResult())) {
				rtpRfpDTO = response.getBody().getUsDomesticPayinInstructionDTO();
			}
		}
		LOGGER.info("RtpRfp Reject Response:::{}", rtpRfpDTO);
		return rtpRfpDTO;
	}

}
