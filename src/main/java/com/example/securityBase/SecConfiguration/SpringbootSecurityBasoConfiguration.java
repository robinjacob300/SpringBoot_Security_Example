package com.example.securityBase.SecConfiguration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@ConditionalOnProperty(name = "SpringbootSecurityBasoConfiguration", havingValue = "true")
public class SpringbootSecurityBasoConfiguration 

{

	@Autowired
	DataSource dataSource;
	
	
	ObjectMapper mapper = new ObjectMapper();
	
	 @Bean
//	 @Order(2)
	    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
	 
	        // InMemoryUserDetailsManager
	        UserDetails admin = User.withUsername("shelok")
	                .password(encoder.encode("123"))
	                .roles("ADMIN", "USER")
	                .build();
	 
	        UserDetails user = User.withUsername("Ejaz")
	                .password(encoder.encode("123"))
	                .roles("USER")
	                .build();
	 
	        return new InMemoryUserDetailsManager(admin, user);
	    }
	 
	    // Configuring HttpSecurity
	 
     @SuppressWarnings({ "removal", "deprecation" })
	@Bean
     public SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception {

 		// @formatter:off
 		http
 				.authorizeHttpRequests((authorize) -> authorize
 						.anyRequest().authenticated()

 				)
 				
 				.httpBasic(withDefaults())
 				.formLogin(withDefaults());
 		
 		// @formatter:on
 		return http.build();
     }
	    
	 
	 
	    // Password Encoding
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	


}
