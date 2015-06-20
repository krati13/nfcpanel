/**
JMathur
 */

package com.springsecurity.nfc.model;

import java.util.Date;

import com.springsecurity.auth.model.User;
import com.springsecurity.auth.util.AuthUtil;
import com.springsecurity.nfc.constants.Constants;

public class Menu implements Updatable, Constants {
	private long id;
	private String name;
	private String description;
	private int quantity;
	private double amount;
	private int prep_time;
	private String category;
	private String URL;
	private Date created_on;
	private String created_by;
	private Date modified_on;
	private String modified_by;
	private long merchant_id;
	private String sortorder;
	private int closed;
	private int discount;
	private Category categoryObj;
	private String subCategory;
	
	public String getSubCategory() {
		if (subCategory!=null && subCategory.contains(PIPE))
			return subCategory.substring(0, subCategory.indexOf(PIPE));
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public Category getCategoryObj() {
		return categoryObj;
	}

	public void setCategoryObj(Category categoryObj) {
		this.categoryObj = categoryObj;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getClosed() {
		return closed;
	}

	public void setClosed(int closed) {
		this.closed = closed;
	}

	public String getSortorder() {
		if (category!=null && category.contains(PIPE)) 
			sortorder=category.substring(category.indexOf(PIPE)+1);
		if(subCategory!=null && subCategory.contains(PIPE))
			sortorder=subCategory.substring(subCategory.indexOf(PIPE)+1);
		
		setSortorder(sortorder);
		
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public long getMerchant_id() {
		if (merchant_id == 0) {
			if (getUser() != null) 
				merchant_id = getUser().getUserId();
		}
		return merchant_id;
	}

	public void setMerchant_id(long merchant_id) {
		this.merchant_id = merchant_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getPrep_time() {
		return prep_time;
	}

	public void setPrep_time(int prep_time) {
		this.prep_time = prep_time;
	}

	public String getCategory() {
		if (category!=null && category.contains(PIPE))
			return category.substring(0, category.indexOf(PIPE));
		
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getModified_by() {
		if (modified_by == null) 
			if (getUser() != null) {
				modified_by = getUser().getName();
			}

		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	private User getUser(){
		User user = (User) AuthUtil.getCurrentSession().getAttribute("user");
		return user;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(getName()).append(PIPE).append(description).append(PIPE).append(quantity).append(PIPE).append(amount).append(PIPE).append(prep_time).append(PIPE).append(getCategory()).append(PIPE).append(URL).append(PIPE).append(getSortorder()).append(PIPE).append(new java.sql.Date(System.currentTimeMillis())).append(PIPE).append(getUser().getName()).append(PIPE).append(new java.sql.Date(System.currentTimeMillis())).append(PIPE).append(getModified_by()).append(PIPE).append(getMerchant_id()).append(PIPE).append(closed).append(PIPE).append(discount).append(PIPE).append(getSubCategory()).toString();
	}

	@Override
	public String toUpdate() {
		return new StringBuilder().append(getName()).append(PIPE).append(description).append(PIPE).append(quantity).append(PIPE).append(amount).append(PIPE).append(prep_time).append(PIPE).append(getCategory()).append(PIPE).append(URL).append(PIPE).append(getSortorder()).append(PIPE).append(new java.sql.Date(System.currentTimeMillis())).append(PIPE).append(getUser().getName()).append(PIPE).append(closed).append(PIPE).append(discount).append(PIPE).append(getSubCategory()).append(PIPE).append(getId()).toString();
	}

	@Override
	public String toDelete() {
		return getId()+"";
	}
}