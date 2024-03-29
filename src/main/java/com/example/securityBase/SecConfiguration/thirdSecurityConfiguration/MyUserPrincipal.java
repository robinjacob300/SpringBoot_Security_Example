package com.example.securityBase.SecConfiguration.thirdSecurityConfiguration;

import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.securityBase.SecurityUserRepo.SecUser;

@ConditionalOnProperty(name = "thirdsecuriyConfig", havingValue = "true")
public class MyUserPrincipal implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SecUser user;
    public String role;
    public MyUserPrincipal(SecUser user) {
        this.user = user;
        if(user.getRole() == null)
        {
        	this.role = new String("KUSER");
        }
        else
        {
        	this.role= user.getRole();
        }
    }
    //...

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        return 
       Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}