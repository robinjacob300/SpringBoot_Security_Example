package com.example.securityBase.SecConfiguration;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.securityBase.SecurityUserRepo.SecUser;
import com.example.securityBase.SecurityUserRepo.SecUserRepository;

@Component
@ConditionalOnProperty(name = "disableExternalUserConfigs", havingValue = "true")
public class ExternalUserConfigs implements AuthenticationProvider {

	@Autowired
	SecUserRepository secUserRepository;
	
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
 
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        int bls =0;
        Iterable<SecUser> usd = secUserRepository.findAll();
        
        for(SecUser seso : usd)
        {
        	if(seso.getUsername().equals(name))
        	{
        		System.out.println("++++++++++++++++++++++++++");
        		System.out.println("Match found");
        		System.out.println("Targetuser: "+ seso.getUsername());
        		bls =1;
        		break;
        	}
        }
        
        if (bls ==1) {
 
            // use the credentials
            // and authenticate against the third-party system
            return new UsernamePasswordAuthenticationToken(
              name, password, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}


