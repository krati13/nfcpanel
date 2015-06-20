package com.springsecurity.nfc.model;

import com.springsecurity.nfc.constants.Constants;
import com.springsecurity.plugin.util.AlphaNum;

public class Category implements Updatable, Constants {
	private long id;
	private String shortName;
	private String fullName;
	private String sortorder;
	private long merchant_id;
	private int levelnumber;
	AlphaNum alphaNum=new AlphaNum();
	
	public int getLevelnumber() {
		return levelnumber;
	}

	public void setLevelnumber(int levelnumber) {
		this.levelnumber = levelnumber;
	}

	public long getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(long merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		setShortName(alphaNum.replaceSpecials(fullName, "").toUpperCase());
		return shortName + PIPE + fullName + PIPE + sortorder + PIPE + merchant_id+PIPE+levelnumber;
	}

	@Override
	public String toUpdate() {
		return new StringBuilder().append(shortName).append(PIPE).append(fullName).append(PIPE).append(sortorder).append(PIPE).append(levelnumber).append(PIPE).append(id).toString();
	}

	@Override
	public String toDelete() {
		return String.valueOf(id);
	}
}