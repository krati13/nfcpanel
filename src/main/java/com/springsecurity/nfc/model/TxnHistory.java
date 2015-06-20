package com.springsecurity.nfc.model;

import java.sql.Timestamp;

public class TxnHistory {
  private long txn_id;
  private String order_id;
  private double tax_amt;
  private double shipping_amt;
  private double order_amt;
  private double total_amt;
  private String bank_txn_id;
  private int status;
  private Timestamp created_on;
  private String created_by;
  private long merchant_id;

  public long getTxn_id() {
	  return txn_id;
  }

  public void setTxn_id(long txn_id) {
	  this.txn_id = txn_id;
  }

  public String getOrder_id() {
	  return order_id;
  }

  public void setOrder_id(String order_id) {
	  this.order_id = order_id;
  }

  public double getTax_amt() {
	  return tax_amt;
  }

  public void setTax_amt(double tax_amt) {
	  this.tax_amt = tax_amt;
  }

  public double getShipping_amt() {
	  return shipping_amt;
  }

  public void setShipping_amt(double shipping_amt) {
	  this.shipping_amt = shipping_amt;
  }

  public double getOrder_amt() {
	  return order_amt;
  }

  public void setOrder_amt(double order_amt) {
	  this.order_amt = order_amt;
  }

  public double getTotal_amt() {
	  return total_amt;
  }

  public void setTotal_amt(double total_amt) {
	  this.total_amt = total_amt;
  }

  public String getBank_txn_id() {
	  return bank_txn_id;
  }	

  public void setBank_txn_id(String bank_txn_id) {
	  this.bank_txn_id = bank_txn_id;
  }

  public int getStatus() {
	  return status;
  }

  public void setStatus(int status) {
	  this.status = status;
  }

  public Timestamp getCreated_on() {
	  return created_on;
  }

  public void setCreated_on(Timestamp created_on) {
	  this.created_on = created_on;
  }

  public String getCreated_by() {
	  return created_by;
  }

  public void setCreated_by(String created_by) {
	  this.created_by = created_by;
  }

  public long getMerchant_id() {
	  return merchant_id;
  }

  public void setMerchant_id(long merchant_id) {
	  this.merchant_id = merchant_id;
  }

  @Override
  public String toString() {
      return order_id+","+tax_amt+","+order_amt+","+shipping_amt+","+total_amt+","+bank_txn_id+","+status+","+created_on+","+created_by;
  }
}