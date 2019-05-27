package com.swapstech.hackathon.common.rest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().regexMatchers("/").permitAll();
	}
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://ec2-3-17-234-174.us-east-2.compute.amazonaws.com:9090/hackathon")
                .allowedMethods("*");
    }
	
}
