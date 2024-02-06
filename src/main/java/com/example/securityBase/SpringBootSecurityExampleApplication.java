package com.example.securityBase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.securityBase.SecConfiguration.CustomSecurityConfigO;
import com.example.securityBase.SecConfiguration.ExternalUserConfigs;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan
(
	    basePackages = "com.example.*"
//	    ,excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, 
//	    classes = {CustomSecurityConfigO.class,
//	    		ExternalUserConfigs.class}
//	    )
	)
public class SpringBootSecurityExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityExampleApplication.class, args);
	}

}
