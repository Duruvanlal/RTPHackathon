package com.swapstech.hackathon.employer.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.hackathon.employer.model.User;
import com.swapstech.hackathon.employer.vo.UserClient;

public class VOMapper {

	public static User convertToUserModel(UserClient client) {
		if (client == null) {
			return null;
		}
		ObjectMapper obj = new ObjectMapper();
		obj.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		User user = obj.convertValue(client, User.class);
		return user;
	}
}