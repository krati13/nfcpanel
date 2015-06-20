/**
 * 
 */
package com.springsecurity.nfc.model;

/**
 * @author Gaurav Oli
 *
 */
public class TxnGraph {
	public String value;
	public String label;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}