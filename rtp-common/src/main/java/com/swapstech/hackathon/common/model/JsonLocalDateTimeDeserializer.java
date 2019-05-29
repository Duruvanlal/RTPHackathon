/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author Duruvanlal TJ
 *
 */
public class JsonLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		 if (parser.isExpectedStartArrayToken()) {
			 return parseArray(parser, context);
	     }
		if (parser.hasTokenId(JsonTokenId.ID_STRING)) {
            String string = parser.getText().trim();
            if (string.length() == 0) {
                return null;
            }
            try {
            	if(string.indexOf("T") > -1) {
            		return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            	}else {
            		return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
            	}
            } catch (DateTimeException e) {
                throw new IOException(e);
            }
        }
        if (parser.hasTokenId(JsonTokenId.ID_EMBEDDED_OBJECT)) {
            return (LocalDateTime) parser.getEmbeddedObject();
        }
        throw context.wrongTokenException(parser, JsonToken.VALUE_STRING, "Expected string.");
	}
	
	private LocalDateTime parseArray(JsonParser jp, DeserializationContext ctxt)
            throws JsonParseException, IOException {
        int year = getNextValue(jp);
        int month = getNextValue(jp);
        int day = getNextValue(jp);
        int h = getNextValue(jp);
        int m = getNextValue(jp);
        int s = getNextValue(jp);
        if (jp.nextToken() != JsonToken.END_ARRAY) {
            throw ctxt.wrongTokenException(jp, JsonToken.END_ARRAY, "after LocalDate ints");
        }
        return LocalDateTime.of(year, month, day, h, m, s);
    }

    private int getNextValue(JsonParser jp) throws IOException, JsonParseException {
        jp.nextToken();
        return new Integer(jp.getText());
    }
}
