package com.swapstech.hackathon.employer.repository.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime>{
	
	public static SimpleDateFormat MMDDYYYYHHMM = new SimpleDateFormat( "MM/dd/yyyy HH:mm:ss");
	
	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		Date dateObj = Date.from(value.atZone(ZoneId.systemDefault()).toInstant());
		gen.writeString(MMDDYYYYHHMM.format(dateObj));
	}

	

}
