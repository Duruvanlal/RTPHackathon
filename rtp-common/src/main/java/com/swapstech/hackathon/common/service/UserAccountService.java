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
	
	public UserPaymentAccount findUserAccountByUpa(String upaCd) {
		return userAcctRepository.findByUpaCd(upaCd);
	}
	
	public User findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}
	
}
