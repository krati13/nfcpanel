package com.springsecurity.auth.model;

import java.io.Serializable;

/**
 *@author Gaurav Oli
 *@version 1.0
 *@since 04/12/2014  
 */
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int grandId;
	private String roleName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGrandId() {
		return grandId;
	}
	public void setGrandId(int grandId) {
		this.grandId = grandId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}