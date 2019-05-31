
package com.swapstech.hackathon.common.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.swapstech.hackathon.common.model.AccountActivity;
import com.swapstech.hackathon.common.model.AccountBalance;
import com.swapstech.hackathon.common.model.AlertDTO;
import com.swapstech.hackathon.common.model.AlertList;
import com.swapstech.hackathon.common.model.CurrencyAmount;
import com.swapstech.hackathon.common.model.RfpApprovalResponse;
import com.swapstech.hackathon.common.model.RfpResponse;
import com.swapstech.hackathon.common.model.RfpResponseEnum;
import com.swapstech.hackathon.common.model.RtpRfpDTO;
import com.swapstech.hackathon.common.model.TokenResponseDTO;
import com.swapstech.hackathon.common.model.TransactionItem;
import com.swapstech.hackathon.common.model.User;
import com.swapstech.hackathon.common.model.UserAcctUpaMapping;
import com.swapstech.hackathon.common.model.UserPaymentAccount;
import com.swapstech.hackathon.common.model.UserUpaMaster;
import com.swapstech.hackathon.common.model.ValueObject;
import com.swapstech.hackathon.common.model.ZillTransaction;
import com.swapstech.hackathon.common.model.ZillTransactionDetails;
import com.swapstech.hackathon.common.model.ZillTransactionStatus;
import com.swapstech.hackathon.common.repository.UserAcctUpaMappingRepository;
import com.swapstech.hackathon.common.repository.UserPymtAcctRepository;
import com.swapstech.hackathon.common.repository.UserUpaMasterRepository;
import com.swapstech.hackathon.common.repository.ZillTransactionRepository;
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
	@Autowired
	UserUpaMasterRepository userUpaMasterRepository;
	@Autowired
	UserAcctUpaMappingRepository userAcctUpaMappingRepository;
	@Autowired
	UserPymtAcctRepository userPymtAcctRepository;
	@Autowired 
	ZillTransactionRepository zillTransactionRepository;
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
		UserUpaMaster requestorUpaMaster = userUpaMasterRepository.findByUpaCd(transaction.getRequestorUpaCd());
		UserAcctUpaMapping requestorMapping = userAcctUpaMappingRepository
				.findByUserUpaMasterId(requestorUpaMaster.getUserUpaMasterId());
		UserPaymentAccount requestorAccount = userAcctService
				.findUserAccountByUserPaymentSAcct(requestorMapping.getUserPaymentAcctId());
		if (requestorAccount != null && StringUtils.isNotBlank(requestorAccount.getAccountNumber())) {
			creditAccount.setValue(requestorAccount.getAccountNumber());
			User creditor = userAcctService.findByUserId(requestorAccount.getUserId());
			if (creditor != null) {
				rfpDto.setCreditorName(creditor.getFirstName() + " " + creditor.getLastName());

				userId = creditor.getUserId();
				pwd = creditor.getPassword();
			}
		}
		rfpDto.setCreditAccountId(creditAccount);
		UserUpaMaster payerUpaMaster = userUpaMasterRepository.findByUpaCd(transaction.getPayerUpaCd());
		UserAcctUpaMapping payerMapping = userAcctUpaMappingRepository
				.findByUserUpaMasterId(payerUpaMaster.getUserUpaMasterId());
		UserPaymentAccount approverAccount = userAcctService
				.findUserAccountByUserPaymentSAcct(payerMapping.getUserPaymentAcctId());
		if (approverAccount != null && StringUtils.isNotBlank(approverAccount.getAccountNumber())) {
			rfpDto.setDebitAccountId(approverAccount.getAccountNumber());
			User payer = userAcctService.findByUserId(approverAccount.getUserId());
			if (payer != null) {
				rfpDto.setDebtorName(payer.getFirstName() + " " + payer.getLastName());
				rfpDto.setAgentMemId(payer.getAgentMemberId());
			}
		}
		// ZillTransactionDetails transDetails =
		// transDetailsService.getZillTransactionDetailByTransCd(transaction.getPaymentTransCode());
		CurrencyAmount currencyAmount = new CurrencyAmount();
		// if (transDetails != null && transDetails.getPaymentAmount() != null) {
		currencyAmount.setCurrency("USD");
		currencyAmount.setAmount(transaction.getPaymentAmount());
		rfpDto.setAmount(currencyAmount);
		// }
		String token = getToken(userId, pwd);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(AUTH, BEARER + token);
		headers.setAccessControlAllowOrigin("*");
		List<HttpMethod> allowedMethods = new ArrayList<>();
		allowedMethods.add(HttpMethod.GET);
		allowedMethods.add(HttpMethod.POST);
		allowedMethods.add(HttpMethod.OPTIONS);
		allowedMethods.add(HttpMethod.PATCH);
		headers.setAccessControlAllowMethods(allowedMethods);
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

	public String getInstructionId(String rtpRefId, String token) {
		String url = ApiUrlConstants.RTP_ALERTS_URL;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(AUTH, BEARER + token);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity request = new HttpEntity(headers);
		AlertList alerts = new AlertList();
		ResponseEntity<AlertList> response = restTemplate.exchange(url, HttpMethod.GET, request, AlertList.class);
		// ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,
		// request, String.class);
		String instructionId = null;
		if (response != null && response.getBody() != null) {
			if (StringUtils.isNotBlank(response.getBody().getStatus().getResult())
					&& "SUCCESSFUL".equalsIgnoreCase(response.getBody().getStatus().getResult())) {
				List<AlertDTO> alertsList = response.getBody().getAlertDTOs();
				for (AlertDTO alertDTO : alertsList) {
					String alertMsg = alertDTO.getMessageBody();
					LOGGER.info("Alert Messages:{}", alertMsg);
					if (StringUtils.isNotBlank(alertMsg) && alertMsg.contains("MoneyRequested")
							&& alertMsg.contains(rtpRefId)) {
						String[] strArr = alertMsg.split("Instruction_Id");
						instructionId = strArr[1].split(",")[0].replaceAll("&#x3a;", "");
						LOGGER.info("Instruction ID:{}", instructionId);

					}
				}
			}
		}
		return instructionId;
	}

	public RtpRfpDTO approveRfp(ZillTransaction transaction) {
		String url = ApiUrlConstants.RFP_ACTION_URL + transaction.getRtpTransId() + "/accept";
		String userId = null;
		String pwd = null;

		RtpRfpDTO rfpDto = new RtpRfpDTO();
		ValueObject creditAccount = new ValueObject();
		UserUpaMaster requestorUpaMaster = userUpaMasterRepository.findByUpaCd(transaction.getRequestorUpaCd());
		UserAcctUpaMapping requestorMapping = userAcctUpaMappingRepository
				.findByUserUpaMasterId(requestorUpaMaster.getUserUpaMasterId());
		UserPaymentAccount requestorAccount = userAcctService
				.findUserAccountByUserPaymentSAcct(requestorMapping.getUserPaymentAcctId());
		if (requestorAccount != null && StringUtils.isNotBlank(requestorAccount.getAccountNumber())) {
			creditAccount.setValue(requestorAccount.getAccountNumber());
			User creditor = userAcctService.findByUserId(requestorAccount.getUserId());
			if (creditor != null) {
				rfpDto.setCreditorName(creditor.getFirstName() + " " + creditor.getLastName());

			}
		}
		rfpDto.setCreditAccountId(creditAccount);
		UserUpaMaster payerUpaMaster = userUpaMasterRepository.findByUpaCd(transaction.getPayerUpaCd());
		UserAcctUpaMapping payerMapping = userAcctUpaMappingRepository
				.findByUserUpaMasterId(payerUpaMaster.getUserUpaMasterId());
		UserPaymentAccount approverAccount = userAcctService
				.findUserAccountByUserPaymentSAcct(payerMapping.getUserPaymentAcctId());
		if (approverAccount != null && StringUtils.isNotBlank(approverAccount.getAccountNumber())) {
			rfpDto.setDebitAccountId(approverAccount.getAccountNumber());
			User payer = userAcctService.findByUserId(approverAccount.getUserId());
			if (payer != null) {
				rfpDto.setDebtorName(payer.getFirstName() + " " + payer.getLastName());
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String formatDateTime = transaction.getCreatedDt().format(formatter);
		rfpDto.setValueDate(formatDateTime);
		rfpDto.setSystemReferenceNo(transaction.getRtpTransId());
		rfpDto.setInstructionId(getInstructionId(transaction.getRtpTransId(), token));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(AUTH, BEARER + token);
		LOGGER.info("RtpRfp Approve Request:::{}", rfpDto);
		HttpEntity<RtpRfpDTO> request = new HttpEntity<RtpRfpDTO>(rfpDto, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<RfpApprovalResponse> response = restTemplate.postForEntity(url, request,
				RfpApprovalResponse.class);
		RtpRfpDTO rtpRfpDTO = null;
		if (response != null && response.getBody() != null) {
			if (StringUtils.isNotBlank(response.getBody().getStatus().getResult())
					&& "SUCCESSFUL".equalsIgnoreCase(response.getBody().getStatus().getResult())) {
				rtpRfpDTO = response.getBody().getPayinDetails();
			}
		}
		LOGGER.info("RtpRfp Approve Response:::{}", rtpRfpDTO);
		return rtpRfpDTO;
	}

	public RtpRfpDTO rejectRfp(ZillTransaction transaction) {
		String url = ApiUrlConstants.RFP_ACTION_URL + transaction.getRtpTransId() + "/reject";
		String userId = null;
		String pwd = null;

		RtpRfpDTO rfpDto = new RtpRfpDTO();
		ValueObject creditAccount = new ValueObject();
		UserUpaMaster requestorUpaMaster = userUpaMasterRepository.findByUpaCd(transaction.getRequestorUpaCd());
		UserAcctUpaMapping requestorMapping = userAcctUpaMappingRepository
				.findByUserUpaMasterId(requestorUpaMaster.getUserUpaMasterId());
		UserPaymentAccount requestorAccount = userAcctService
				.findUserAccountByUserPaymentSAcct(requestorMapping.getUserPaymentAcctId());
		if (requestorAccount != null && StringUtils.isNotBlank(requestorAccount.getAccountNumber())) {
			creditAccount.setValue(requestorAccount.getAccountNumber());
			User creditor = userAcctService.findByUserId(requestorAccount.getUserId());
			if (creditor != null) {
				rfpDto.setCreditorName(creditor.getFirstName() + " " + creditor.getLastName());

			}
		}
		rfpDto.setCreditAccountId(creditAccount);
		UserUpaMaster payerUpaMaster = userUpaMasterRepository.findByUpaCd(transaction.getPayerUpaCd());
		UserAcctUpaMapping payerMapping = userAcctUpaMappingRepository
				.findByUserUpaMasterId(payerUpaMaster.getUserUpaMasterId());
		UserPaymentAccount approverAccount = userAcctService
				.findUserAccountByUserPaymentSAcct(payerMapping.getUserPaymentAcctId());
		if (approverAccount != null && StringUtils.isNotBlank(approverAccount.getAccountNumber())) {
			rfpDto.setDebitAccountId(approverAccount.getAccountNumber());
			User payer = userAcctService.findByUserId(approverAccount.getUserId());
			if (payer != null) {
				rfpDto.setDebtorName(payer.getFirstName() + " " + payer.getLastName());
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String formatDateTime = transaction.getCreatedDt().format(formatter);
		rfpDto.setValueDate(formatDateTime);
		rfpDto.setSystemReferenceNo(transaction.getRtpTransId());
		rfpDto.setInstructionId(getInstructionId(transaction.getRtpTransId(), token));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(AUTH, BEARER + token);
		LOGGER.info("RtpRfp Reject Request:::{}", rfpDto);
		HttpEntity<RtpRfpDTO> request = new HttpEntity<RtpRfpDTO>(rfpDto, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<RfpApprovalResponse> response = restTemplate.postForEntity(url, request,
				RfpApprovalResponse.class);
		RtpRfpDTO rtpRfpDTO = null;
		if (response != null && response.getBody() != null) {
			if (StringUtils.isNotBlank(response.getBody().getStatus().getResult())
					&& "SUCCESSFUL".equalsIgnoreCase(response.getBody().getStatus().getResult())) {
				rtpRfpDTO = response.getBody().getPayinDetails();
			}
		}
		LOGGER.info("RtpRfp Reject Response:::{}", rtpRfpDTO);
		return rtpRfpDTO;
	}

	public List<TransactionItem> getRtpTransactions(String acctNum) {
		UserPaymentAccount account = userAcctService.findByAccountNumber(acctNum);
		String token = null;
		List<TransactionItem> items = null;
		if (account != null && StringUtils.isNotBlank(account.getUserId())) {
			User user = userAcctService.findByUserId(account.getUserId());
			if (user != null) {
				token = getToken(user.getUserId(), user.getPassword());
			}
			String url = ApiUrlConstants.RTP_ACCOUNTS_URL + acctNum
					+ "/transactions?noOfTransactions=5&searchBy=LNT&locale=en";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(AUTH, BEARER + token);

			RestTemplate restTemplate = new RestTemplate();
			HttpEntity request = new HttpEntity(headers);
			items = new ArrayList<>();
			AccountActivity activity = new AccountActivity();
			ResponseEntity<AccountActivity> response = restTemplate.exchange(url, HttpMethod.GET, request,
					AccountActivity.class);
			if (response != null && response.getBody() != null) {
				if (StringUtils.isNotBlank(response.getBody().getStatus().getResult())
						&& "SUCCESSFUL".equalsIgnoreCase(response.getBody().getStatus().getResult())) {
					items = response.getBody().getItems();
					for (TransactionItem transactionItem : items) {
						LOGGER.info("TransactionItem:{}", transactionItem);
					}
				}
			}

		}
		return items;

	}

	public AccountBalance getBalance(String acctNum) {
		UserPaymentAccount account = userAcctService.findByAccountNumber(acctNum);
		String token = null;
		AccountBalance balance = null;
		if (account != null && StringUtils.isNotBlank(account.getUserId())) {
			User user = userAcctService.findByUserId(account.getUserId());
			if (user != null) {
				token = getToken(user.getUserId(), user.getPassword());
			}
			String url = ApiUrlConstants.RTP_ACCOUNTS_URL + acctNum;
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(AUTH, BEARER + token);

			RestTemplate restTemplate = new RestTemplate();
			HttpEntity request = new HttpEntity(headers);
			balance = new AccountBalance();
			ResponseEntity<AccountBalance> response = restTemplate.exchange(url, HttpMethod.GET, request,
					AccountBalance.class);
			if (response != null && response.getBody() != null) {
				if (StringUtils.isNotBlank(response.getBody().getStatus().getResult())
						&& "SUCCESSFUL".equalsIgnoreCase(response.getBody().getStatus().getResult())) {
					balance = response.getBody();
					LOGGER.info("getBalance:{}", balance);

				}
			}

		}
		return balance;
	}

	public boolean checkRfpStatus(ZillTransaction transaction) {
		String userId = null;
		String pwd = null;
		boolean isRfpTransmitted = false;
		UserUpaMaster payerUpaMaster = userUpaMasterRepository.findByUpaCd(transaction.getPayerUpaCd());
		UserAcctUpaMapping payerMapping = userAcctUpaMappingRepository
				.findByUserUpaMasterId(payerUpaMaster.getUserUpaMasterId());
		UserPaymentAccount approverAccount = userAcctService
				.findUserAccountByUserPaymentSAcct(payerMapping.getUserPaymentAcctId());
		if (approverAccount != null && StringUtils.isNotBlank(approverAccount.getAccountNumber())) {
			User payer = userAcctService.findByUserId(approverAccount.getUserId());
			if (payer != null) {
				userId = payer.getUserId();
				pwd = payer.getPassword();
			}
		}
		String token = getToken(userId, pwd);
		String instructionId = getInstructionId(transaction.getRtpTransId(), token);
		LOGGER.info("checkRfpStatus Response:::{}", instructionId);
		ZillTransactionDetails txnDetails=transDetailsService.getZillTransactionDetailByTransCd(transaction.getPaymentTransCode());
		if (StringUtils.isNotBlank(txnDetails.getTransStatus()) && ZillTransactionStatus.PENDING_APPROVAL.name().equalsIgnoreCase(txnDetails.getTransStatus())) {
			isRfpTransmitted = true;			
		}
		
		if (StringUtils.isNotBlank(instructionId) && null !=txnDetails && StringUtils.isNotBlank(txnDetails.getTransStatus())) {
			if (StringUtils.isNotBlank(instructionId) && ZillTransactionStatus.BANK_REVIEW.name().equalsIgnoreCase(txnDetails.getTransStatus())) {
				isRfpTransmitted = true;
				txnDetails.setTransStatus(ZillTransactionStatus.PENDING_APPROVAL.name());
				transDetailsService.updateZillTransactionDetails(txnDetails);
			}
		}
		return isRfpTransmitted;
	}
	
	public void listenNotifications(List<ZillTransaction> zillTxnList,String userId) {		
		String token = getToken(userId, "Welcome@1");
		String url = ApiUrlConstants.RTP_ALERTS_URL;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(AUTH, BEARER + token);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity request = new HttpEntity(headers);
		AlertList alerts = new AlertList();
		ResponseEntity<AlertList> response = restTemplate.exchange(url, HttpMethod.GET, request, AlertList.class);		
		if (response != null && response.getBody() != null) {
			if (StringUtils.isNotBlank(response.getBody().getStatus().getResult())
					&& "SUCCESSFUL".equalsIgnoreCase(response.getBody().getStatus().getResult())) {
				List<AlertDTO> alertsList = response.getBody().getAlertDTOs();
				for (AlertDTO alertDTO : alertsList) {
					String alertMsg = alertDTO.getMessageBody();
					//LOGGER.info("Alert Messages:{}", alertMsg);
					for (ZillTransaction zillTransaction : zillTxnList) {
					//	LOGGER.info("Listener Notifications Response:::{}", zillTransaction.getRtpTransId());
						String instructionId = null;
						if (StringUtils.isNotBlank(alertMsg) && alertMsg.contains("MoneyRequested") && StringUtils.isNotBlank(zillTransaction.getRtpTransId())
								&& alertMsg.contains(zillTransaction.getRtpTransId()) ) {
							String[] strArr = alertMsg.split("Instruction_Id");
							instructionId = strArr[1].split(",")[0].replaceAll("&#x3a;", "");
							LOGGER.info("Instruction ID:{}", instructionId);
							zillTransaction.setInstructionId(instructionId);
							zillTransactionRepository.save(zillTransaction);
							ZillTransactionDetails txnDetails=transDetailsService.getZillTransactionDetailByTransCd(zillTransaction.getPaymentTransCode());
							if (StringUtils.isNotBlank(instructionId) && null !=txnDetails && StringUtils.isNotBlank(txnDetails.getTransStatus())) {
								if (StringUtils.isNotBlank(instructionId) && ZillTransactionStatus.BANK_REVIEW.name().equalsIgnoreCase(txnDetails.getTransStatus())) {				
									txnDetails.setTransStatus(ZillTransactionStatus.PENDING_APPROVAL.name());
									transDetailsService.updateZillTransactionDetails(txnDetails);
								}
							}	

						}
					}
				}
			}
		}	
	}
}
