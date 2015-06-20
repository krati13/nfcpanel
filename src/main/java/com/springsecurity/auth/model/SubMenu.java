package com.springsecurity.auth.model;

import java.io.Serializable;

/**
 *@author Gaurav Oli
 *@version 1.0
 *@since 04/12/2014 
 */
public class SubMenu implements Serializable{
	private static final long serialVersionUID = -5032175286867393033L;
	private int id;
	private String parentId;
	private String name;
	private String uri;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}