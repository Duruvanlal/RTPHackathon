package com.swapstech;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
//@EnableEurekaClient
//@EnableCircuitBreaker
//@EnableResourceServer
//@EnableTransactionManagement
//@EnableCasClient
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableAsync(proxyTargetClass=true)
@EnableJpaRepositories("com.swapstech.hackathon.employer.repository")
@ComponentScan({"com.swapstech.hackathon.employer.config","com.swapstech.hackathon.employer.rest","com.swapstech.hackathon.employer.service"})
public class Application {
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
