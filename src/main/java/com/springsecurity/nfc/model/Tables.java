package com.springsecurity.nfc.model;

import com.springsecurity.nfc.constants.Constants;

public class Tables implements Updatable, Constants{
	private int Id;
	private String TID;
	private int status;
	private int reserved;
	private int merchant_id;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getReserved() {
		return reserved;
	}
	public void setReserved(int reserved) {
		this.reserved = reserved;
	}
	public int getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(int merchant_id) {
		this.merchant_id = merchant_id;
	}
	
	@Override
	public String toString() {
		return TID + PIPE + status+ PIPE + reserved + PIPE + merchant_id;
	}
	@Override
	public String toUpdate() {
		return null;
	}
	@Override
	public String toDelete() {
		return null;
	}
}