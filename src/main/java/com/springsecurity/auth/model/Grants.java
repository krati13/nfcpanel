package com.springsecurity.auth.model;

import java.io.Serializable;

/**
 *@author Gaurav Oli
 *@version 1.0
 *@since 04/12/2014 
 */
public class Grants implements Serializable {
	private static final long serialVersionUID = 4115494221582792586L;
	private int id;
	private String menuId;
	private boolean isAddAllowed;
	private boolean isEditAllowed;
	private boolean isDeleteAllowed;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public boolean isAddAllowed() {
		return isAddAllowed;
	}
	public void setAddAllowed(boolean isAddAllowed) {
		this.isAddAllowed = isAddAllowed;
	}
	public boolean isEditAllowed() {
		return isEditAllowed;
	}
	public void setEditAllowed(boolean isEditAllowed) {
		this.isEditAllowed = isEditAllowed;
	}
	public boolean isDeleteAllowed() {
		return isDeleteAllowed;
	}
	public void setDeleteAllowed(boolean isDeleteAllowed) {
		this.isDeleteAllowed = isDeleteAllowed;
	}
}