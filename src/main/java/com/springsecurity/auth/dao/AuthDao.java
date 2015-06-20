package com.springsecurity.auth.dao;

import java.util.List;

import com.springsecurity.auth.model.PMenu;
import com.springsecurity.auth.model.User;

public interface AuthDao {
	User authenticate(User user);
	User findUserByName(User user);
	List<PMenu> menuByRole(int roleId);
}
