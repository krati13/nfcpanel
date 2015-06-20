/**
JMathur
 */

package com.springsecurity.nfc.model;

import java.sql.Date;

import com.springsecurity.auth.model.User;
import com.springsecurity.auth.util.AuthUtil;
import com.springsecurity.auth.util.DateUtil;
import com.springsecurity.nfc.constants.Constants;

public class Merchant implements Updatable, Constants {
	private int id;
	private String firstName;
	private String lastName;
	private String businessName;
	private String email;
	private String mobile;
	private String landline;
	private int tableCount;
	private String paymentOption;
	private Date validFrom;
	private Date validTo;
	private String city;
	private String state;
	private String pincode;
	private String website;
	private String create_on;
	private String created_by;
	private String modified_on;
	private String modified_by;
	private String ip_address;
	private String merchantType;
	private String fbLikeUrl;
	
	
	
	public String getFbLikeUrl() {
		return fbLikeUrl;
	}

	public void setFbLikeUrl(String fbLikeUrl) {
		this.fbLikeUrl = fbLikeUrl;
	}
	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	public String getModified_by() {
		if (modified_by == null) {
			if (getUser() != null) {
				modified_by = getUser().getName();
			}
		}
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getCreate_on() {
		return create_on;
	}

	public void setCreate_on(String create_on) {
		this.create_on = create_on;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public int getTableCount() {
		return tableCount;
	}

	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;
	}

	public String getPaymentOption() {
		if (paymentOption==null || paymentOption.isEmpty())
			paymentOption="0";

		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		if (paymentOption==null || paymentOption.isEmpty())
			paymentOption="0";

		this.paymentOption = paymentOption;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	@Override
	public String toString() {
		return new StringBuilder(firstName).append(PIPE).append(lastName)
				.append(PIPE).append(businessName).append(PIPE).append(email)
				.append(PIPE).append(mobile).append(PIPE).append(landline)
				.append(PIPE).append(tableCount).append(PIPE)
				.append(getPaymentOption()).append(PIPE).append(DateUtil.getSQLDateFormat(validFrom))
				.append(PIPE).append(DateUtil.getSQLDateFormat(validTo)).append(PIPE).append(city)
				.append(PIPE).append(state).append(PIPE).append(pincode)
				.append(PIPE).append(website).append(PIPE)
				.append(new java.sql.Date(System.currentTimeMillis()))
				.append(PIPE)
				.append(getUser().getName()).append(PIPE)
				.append(new java.sql.Date(System.currentTimeMillis()))
				.append(PIPE)
				.append(getUser().getName()).append(PIPE).append(AuthUtil.getRequest().getRemoteAddr()).append(PIPE).append(merchantType).append(PIPE).append(fbLikeUrl)
				.toString();
	}

	@Override
	public String toUpdate() {
		return new StringBuilder(firstName).append(PIPE).append(lastName).append(PIPE).append(businessName).append(PIPE).append(email).append(PIPE).append(mobile).append(PIPE).append(landline).append(PIPE).append(tableCount).append(PIPE).append(getPaymentOption()).append(PIPE).append(DateUtil.getSQLDateFormat(validFrom)).append(PIPE).append(DateUtil.getSQLDateFormat(validTo)).append(PIPE).append(city).append(PIPE).append(state).append(PIPE).append(pincode).append(PIPE).append(website).append(PIPE).append(new java.sql.Date(System.currentTimeMillis())).append(PIPE).append(getUser().getName()).append(PIPE).append(AuthUtil.getRequest().getRemoteAddr()).append(PIPE).append(merchantType).append(PIPE).append(fbLikeUrl).append(PIPE).append(id).toString();
	}

	@Override
	public String toDelete() {
		return id+EMPTY;
	}
	
	private User getUser(){
		User user = (User) AuthUtil.getCurrentSession().getAttribute("user");
		return user;
	}
}
