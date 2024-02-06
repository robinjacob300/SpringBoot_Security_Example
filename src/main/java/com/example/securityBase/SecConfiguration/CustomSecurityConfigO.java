package com.example.securityBase.SecConfiguration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletRequest;


@Configuration
@EnableWebSecurity
@ConditionalOnProperty(name = "disableExternalUserConfigs", havingValue = "true")
public class CustomSecurityConfigO {

	
	@Autowired
    private ExternalUserConfigs authProvider;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

    @SuppressWarnings({ "deprecation", "removal" })
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 /*       http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();  */
    	

		http
		.csrf().disable()
			.authorizeHttpRequests((authorize) -> authorize
//					.requestMatchers(HttpMethod.POST).permitAll()
					.anyRequest().authenticated()

			)
			
			.httpBasic(withDefaults())
			.formLogin(withDefaults());

        
        
	// @formatter:on
        return http.build();
    }

}
