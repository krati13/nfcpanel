package com.springsecurity.nfc.model;

public class DateSearch {
	private String created_from;
	private String created_to;
	
	public DateSearch(String created_from, String created_to) {
		this.created_from = created_from;
		this.created_to = created_to;
	}
	
	public String getCreated_from() {
		return created_from;
	}
	public void setCreated_from(String created_from) {
		this.created_from = created_from;
	}
	public String getCreated_to() {
		return created_to;
	}
	public void setCreated_to(String created_to) {
		this.created_to = created_to;
	}
}
