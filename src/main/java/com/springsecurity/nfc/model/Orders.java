package com.springsecurity.nfc.model;

import java.sql.Timestamp;

public class Orders {
	private long id;
	private String order_id;
	private String table_id;
	private long menu_id;
	private String menu_name;
	private int quantity;
	private double amount;
	private String status;
	private long merchant_id;
	private Timestamp created_on;
	private Timestamp modified_on;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getTable_id() {
		return table_id;
	}
	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}
	public long getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(long menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(long merchant_id) {
		this.merchant_id = merchant_id;
	}
	public Timestamp getCreated_on() {
		return new Timestamp(System.currentTimeMillis());
	}
	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}
	public Timestamp getModified_on() {
		return new Timestamp(System.currentTimeMillis());
	}
	public void setModified_on(Timestamp modified_on) {
		this.modified_on = modified_on;
	}
	
	@Override
	public String toString() {
		return order_id+","+table_id+","+menu_id+","+quantity+","+amount+","+status+","+getCreated_on()+","+getModified_on()+","+merchant_id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (menu_id ^ (menu_id >>> 32));
		result = prime * result + (int) (merchant_id ^ (merchant_id >>> 32));
		result = prime * result
				+ ((order_id == null) ? 0 : order_id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (menu_id != other.menu_id)
			return false;
		if (merchant_id != other.merchant_id)
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		return true;
	}
	
	
}