/**
 * 
 */
package com.swapstech.hackathon.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.swapstech.hackathon.common.repository.UserRepository;
import com.swapstech.hackathon.common.model.User;
import com.swapstech.hackathon.common.model.UserPaymentAccount;
import com.swapstech.hackathon.common.repository.UserPymtAcctRepository;

/**
 * @author Duruvanlal TJ
 *
 */
@Service
public class UserAccountService {
	@Autowired
	UserPymtAcctRepository userAcctRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public UserPaymentAccount findUserAccountByUserPaymentSAcct(String userPaymentAcctId) {
		return userAcctRepository.findByUserPaymentAcctId(userPaymentAcctId);
	}
	
	public User findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}
	
}
