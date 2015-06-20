package com.springsecurity.auth.model;

import java.io.Serializable;
import java.util.List;

import com.springsecurity.common.query.Constants;
import com.springsecurity.nfc.model.Updatable;

/**
 * @author Gaurav Oli
 * @version 1.0
 * @since 04/12/2014
 */
public class User implements Serializable, Updatable {
	private static final long serialVersionUID = 1L;
	private long userId;
	private String loginName;
	private String password;
	private String status;
	private String name;
	private String email;
	private String DOB;
	private Role role;
	private String errorMessage;
	private String welcomeMessage;
	private boolean isAuth;
	private List<PMenu> menu;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
		setWelcomeMessage(role);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isAuth() {
		return isAuth;
	}

	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}

	public List<PMenu> getMenu() {
		return menu;
	}

	public void setMenu(List<PMenu> menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User:");
		sb.append(this.getName());
		sb.append(Constants.PIPE + this.getEmail());
		sb.append(Constants.PIPE + this.getUserId());
		return sb.toString();
	}

	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	public void setWelcomeMessage(Role role) {
		if (role != null) {
			String roleName = role.getRoleName();
			if (Constants.RoleConstants.ROLE_ADMIN.equals(roleName)) {
				welcomeMessage="<div class='welcomeMessage'>Being an Admin, you can Add / Update / Delete User.<br>You can browse across menu to perform any operation.</div>";
			} else if (Constants.RoleConstants.ROLE_USER.equals(roleName)) {
				welcomeMessage="<div class='welcomeMessage'>Being an User, you can Modify your basic details.<br>You can browse across menu to perform allowed operation.</div>";
			}
		}
	}

	@Override
	public String toUpdate() {
		return null;
	}

	@Override
	public String toDelete() {
		return getUserId()+"";
	}
}