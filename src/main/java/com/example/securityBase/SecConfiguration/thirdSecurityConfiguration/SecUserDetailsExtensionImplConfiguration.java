package com.example.securityBase.SecConfiguration.thirdSecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securityBase.SecurityUserRepo.SecUser;
import com.example.securityBase.SecurityUserRepo.SecUserRepository;

@Service
@ConditionalOnProperty(name = "thirdsecuriyConfig", havingValue = "true")
public class SecUserDetailsExtensionImplConfiguration 
implements UserDetailsService {

    @Autowired
    private SecUserRepository userRepository;


    public PasswordEncoder encoder = new BCryptPasswordEncoder(11);
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        Iterable<SecUser> users = userRepository.findAll();
        
        SecUser user;
        
        for (SecUser r: users)
        {
        	if(r.getUsername().equals(username))
        	{
        		System.out.println("USER MATCHES CONFIGURATION 3");
        		user = new SecUser();
        		user.setUsername(r.getUsername());
        		user.setPassword(encoder.encode(r.getPassword()));
        		user.setSecretQuestion(r.getSecretQuestion());

                return new MyUserPrincipal(user);
        	}
        }
        
 /*       if (user == null) {
            throw new UsernameNotFoundException(username);
        }*/
        return null;

    }
}