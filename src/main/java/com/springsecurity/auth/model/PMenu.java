package com.springsecurity.auth.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *@author Gaurav Oli
 *@version 1.0
 *@since 04/12/2014 
 */
public class PMenu implements Serializable {
	private static final long serialVersionUID = 5327148922795412733L;
	private int id;
	private String name;
	private String uri;
	private String identifier;
	private List<PMenu> subMenu = new ArrayList<PMenu>(); 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public List<PMenu> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<PMenu> subMenu) {
		this.subMenu = subMenu;
	}
	
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("(Menu :");
		sb.append(this.getId());
		sb.append(","+this.getName());
		sb.append(","+this.getUri()+")");
		return sb.toString();
	}
}