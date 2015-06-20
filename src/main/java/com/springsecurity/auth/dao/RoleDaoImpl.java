/**
JMathur
 */

package com.springsecurity.auth.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springsecurity.auth.model.Role;
import com.springsecurity.common.query.Queries;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Role> getRoleList() {
		List<Role> listRoles = new ArrayList<Role>();
		try {
			listRoles = jdbcTemplate.query(Queries.RoleQueries.GET_ROLES,new BeanPropertyRowMapper<Role>(Role.class));
		} catch (Exception e) {
		}
		return listRoles;
	}

}
