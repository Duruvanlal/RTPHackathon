package com.swapstech.hackathon.employer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.swapstech.hackathon.employer.model.Token;
import com.swapstech.hackathon.employer.util.JWT;



@RestController
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	public static final String APIURL = "https://sandbox.api.yodlee.com/ysl/";
	public static final String APIKEY = "0098bef0-5641f0f8-4ccf-4fa2-be52-4867fb4746fc";
	public static final String NODEURL = "https://node.sandbox.yodlee.com/authenticate/restserver";
	
	@RequestMapping(value = "ping", method = {RequestMethod.GET})
	public ResponseEntity<Void> ping() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "token", method = {RequestMethod.GET})
	public ResponseEntity<Object> token() {
		
		Token userToken = new Token();
		
		String name = "sbMemqqMhlTDiKpyZp1";
		String key = APIKEY;
		
		JWT jwt = new JWT();
		String privateKey =jwt.readPrivKey("privateKey.txt");

		System.out.println("\n privateKey " + privateKey);
		 
		String bearer=null;
		String token=null;
		
		int validity=1000;
		
		try {
			// Calling JWT util to generate the token
			
			 token=JWT.getJWTToken(name, key, privateKey,validity);
			 
			 bearer="Bearer "+token;
			 
			 userToken.setJwtToken(bearer);	

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(userToken);
	}
	
}