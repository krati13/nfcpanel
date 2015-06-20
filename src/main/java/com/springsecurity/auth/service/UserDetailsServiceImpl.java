package com.springsecurity.auth.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsecurity.auth.dao.AuthDao;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AuthDao authDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) {
		//User user = authDao.findUserByName(username);
    	String password = "kamal";
    	boolean enabled = true;// user.getStatus().equals(UserStatus.ACTIVE);
    	boolean accountNonExpired = true; //user.getStatus().equals(UserStatus.ACTIVE);
    	boolean credentialsNonExpired = true; //user.getStatus().equals(UserStatus.ACTIVE);
    	boolean accountNonLocked = true; //user.getStatus().equals(UserStatus.ACTIVE);
    	
    	Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	authorities.add(new GrantedAuthorityImpl("User"));
    	/*for (Role role : user.getRoles())
    		authorities.add(new GrantedAuthorityImpl(role.getRoleName()));*/
    	
    	org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    	return securityUser;
	}
	
}
