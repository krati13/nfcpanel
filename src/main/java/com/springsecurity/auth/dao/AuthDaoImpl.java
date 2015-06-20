package com.springsecurity.auth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springsecurity.auth.model.PMenu;
import com.springsecurity.auth.model.Role;
import com.springsecurity.auth.model.User;
import com.springsecurity.common.query.AuthQueries;

@Repository
public class AuthDaoImpl implements AuthDao, AuthQueries {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User authenticate(User user) {
		try {
			String password = jdbcTemplate.queryForObject(IS_USER_EXIST, new Object[]{user.getLoginName()}, String.class);
			if(null!=password || !"".equals(password)) {
				if(password.equals(user.getPassword()))
					user.setAuth(true);
				else {
					user.setAuth(false);
					user.setErrorMessage("Password do not match");
				}
			}else{
				user.setAuth(false);
				user.setErrorMessage("Invalid username provided.");
			}
		}
		catch (Exception e) {
			user.setErrorMessage("Invalid username provided.");
		}
		return user;
	}
	
	
	@Override
	public User findUserByName(User user) {
		final Role role = new Role();
		try {
			user = jdbcTemplate.queryForObject(GET_USER_DETAILS, new Object[]{user.getLoginName()}, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User vo = new User();
					vo.setUserId(rs.getLong("UID"));
					vo.setName(rs.getString("USERNAME"));
					vo.setPassword(rs.getString("PASSWORD"));
					vo.setStatus(rs.getString("STATUS"));
					vo.setLoginName(rs.getString("LOGINNAME"));
					vo.setEmail(rs.getString("EMAIL"));
					vo.setDOB(rs.getString("DOB"));
					
					role.setId(rs.getInt("ID"));
					role.setRoleName(rs.getString("ROLENAME"));
					vo.setRole(role);
					return vo;
				}
			});
		}
		catch (Exception e) {
			user.setErrorMessage(e.getMessage());
		}
		return user;
	}

	@Override
	public List<PMenu> menuByRole(int roleId) {
		try {
			return jdbcTemplate.query(GET_USER_MENU, new Object[]{roleId}, new RowMapper<PMenu>() {
				@Override
				public PMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
					PMenu menu = new PMenu();
					menu.setId(rs.getInt("ID"));
					menu.setName(rs.getString("NAME"));
					menu.setUri(rs.getString("URI"));
					menu.setIdentifier(rs.getString("IDENTIFIER"));
					return menu;
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}