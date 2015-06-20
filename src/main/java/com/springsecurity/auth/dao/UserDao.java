package com.springsecurity.auth.dao;

import com.springsecurity.auth.model.User;

public interface UserDao {
	public User addUser(User user);
	public User editUser(User user);
	public User deleteUser(User user);
	public int getMaxUserId();
	void addUserRole(int user_id, int role_id);
}
