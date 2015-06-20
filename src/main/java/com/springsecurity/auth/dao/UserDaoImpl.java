package com.springsecurity.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springsecurity.auth.model.User;
import com.springsecurity.common.query.Queries;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User addUser(User user) {
		try {
			jdbcTemplate.update(Queries.UserQueries.ADD_USER,new Object[]{user.getUserId(),user.getName(),user.getPassword(),user.getStatus(),user.getLoginName(),user.getEmail()});
		}
		catch (Exception e) {
			user.setErrorMessage(e.getMessage());
			//throw e;
		}
		return user;
	}
	
	@Override
	public void addUserRole(int user_id,int role_id) {
		try {
			jdbcTemplate.update(Queries.RolesAndUsersQueries.ASSIGN_ROLE,new Object[]{user_id,role_id});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User editUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxUserId() {
		Integer maxUserId=jdbcTemplate.queryForObject(Queries.UserQueries.MAX_USER_ID,Integer.class);
		return maxUserId;
	}

}
