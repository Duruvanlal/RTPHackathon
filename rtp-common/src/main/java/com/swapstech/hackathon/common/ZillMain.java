/**
 * 
 */
package com.swapstech.hackathon.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author Duruvanlal TJ
 *
 */

@SpringBootApplication

public class ZillMain {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZillMain.class);
	public static void main(String[] args) {
		LOGGER.info("Application rtp-common is starting");
		 SpringApplication.run(ZillMain.class, args);
	}
	
	
}
