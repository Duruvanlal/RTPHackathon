package com.swapstech.hackathon.common.repository.util;

import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonAttributeConverter implements AttributeConverter<Map<String,String>,String>{
	private final static ObjectMapper objectMapper = new ObjectMapper();
	@Override
	@NotNull
	public String convertToDatabaseColumn(@NotNull Map<String,String> attributes) {
		try {
			return objectMapper.writeValueAsString(attributes);
		}catch(Exception ex) {
			return null;
		}
			
	}

	@Override
	@NotNull
	public Map<String,String> convertToEntityAttribute(@NotNull String attributes) {
		try {
			return objectMapper.readValue(attributes, Map.class);
		}catch(Exception ex) {
			return null;
		}
		
	}

}
