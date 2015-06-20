package com.springsecurity.auth.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.springsecurity.auth.dao.AuthDao;
import com.springsecurity.auth.model.PMenu;
import com.springsecurity.auth.model.User;
import com.springsecurity.auth.service.UserDetailsServiceImpl;
import com.springsecurity.auth.util.AuthUtil;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	UserDetailsServiceImpl userDetailsImpl;

	@Autowired
	AuthDao authDAO;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		User user = new User();
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (null == username || username.trim().isEmpty())
			throw new BadCredentialsException("Username not found.");
		else if (null == password || password.trim().isEmpty())
			throw new BadCredentialsException("Password not found.");

		
		user.setLoginName(username);
		user.setPassword(password);
		user = authDAO.authenticate(user);

		if (user.getErrorMessage() != null)
			throw new BadCredentialsException(user.getErrorMessage());

		user = authDAO.findUserByName(user);
		List<PMenu> menu = createMenu(user.getRole().getId());
		user.setMenu(menu);

		// Grant Authority
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>(1);
		grantedAuths.add(new GrantedAuthorityImpl(user.getRole().getRoleName()));

		Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuths);

		HttpSession session = AuthUtil.session();
		session.setAttribute("user", user);
		// userDetailsImpl.loadUserByUsername(username);
		return auth;
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private List<PMenu> createMenu(int id) {
		List<PMenu> menu = authDAO.menuByRole(id);
		List<PMenu> newMenu = new ArrayList<PMenu>(menu.size());
		PMenu menuu = null;
		for (PMenu m : menu) {
			String keyShort = m.getIdentifier().substring(0,
					m.getIdentifier().indexOf("00"));
			int level = keyShort.length() / 2;
			switch (level) {
			case 1:
				//if (menuu == null)
					menuu = m;
				//else {
					newMenu.add(menuu);
				//	menuu = m;
				//}
				break;
			case 2:
				menuu.getSubMenu().add(m);
				break;
			case 3:
				menuu.getSubMenu().get(0).getSubMenu().add(m);
				break;
			case 4:
				menuu.getSubMenu().get(0).getSubMenu().get(0).getSubMenu().add(m);
				break;
			}
		}
		return newMenu;
	}
}