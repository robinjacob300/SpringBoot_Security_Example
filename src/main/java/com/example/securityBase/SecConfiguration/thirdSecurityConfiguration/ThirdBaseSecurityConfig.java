package com.example.securityBase.SecConfiguration.thirdSecurityConfiguration;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@ConditionalOnProperty(name = "thirdsecuriyConfig", havingValue = "true")
public class ThirdBaseSecurityConfig {

	
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecUserDetailsExtensionImplConfiguration userDetailsService;
    
    @Value("${spring.h2.console.enabled}")
    String h2_console_enabled;
    
    @Bean
    public UserDetailsManager users(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder());
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
        return jdbcUserDetailsManager;
    }
    
	@Bean
    public SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception {

		// @formatter:off
		http
		.csrf().disable()
				.authorizeHttpRequests((authorize) -> authorize
						.anyRequest().authenticated()

				)
				
				.httpBasic(withDefaults())
				.formLogin(withDefaults());
		
		System.out.println("\n +++++++ SEC CONFIG \n\n\n");
		System.out.println("h2 console present : "+ h2_console_enabled);
		
		if(h2_console_enabled.equals("true"))
		{
			http.headers().frameOptions().disable();
		}
		// @formatter:on
		return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    

}
