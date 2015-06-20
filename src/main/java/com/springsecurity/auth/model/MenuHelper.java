package com.springsecurity.auth.model;

public class MenuHelper implements Comparable<MenuHelper> {
	private int id;
	private String name;
	private String uri;
	private long identifier;
	
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
	public long getIdentifier() {
		return identifier;
	}
	public void setIdentifier(long identifier) {
		this.identifier = identifier;
	}

	@Override
	public int compareTo(MenuHelper obj) {
		return obj.getIdentifier()>identifier?-1:1;
	}
}