package com.springsecurity.nfc.model;

public class DataSetsTxn {
	private String label;
	private String fillColor;
	private String strokeColor;
	private String highlightFill;
	private String highlightStroke;
	private String[] data;
	
	public DataSetsTxn(String[] data) {
		this.label="Txn Graph";
		this.fillColor = "#337ab7";
		this.strokeColor = "#2e6da4";
		this.highlightFill = "#2e6da4";
		this.highlightStroke = "#2e6da4";
		this.data = data;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getFillColor() {
		return fillColor;
	}
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}
	public String getStrokeColor() {
		return strokeColor;
	}
	public void setStrokeColor(String strokeColor) {
		this.strokeColor = strokeColor;
	}
	public String getHighlightFill() {
		return highlightFill;
	}
	public void setHighlightFill(String highlightFill) {
		this.highlightFill = highlightFill;
	}
	public String getHighlightStroke() {
		return highlightStroke;
	}
	public void setHighlightStroke(String highlightStroke) {
		this.highlightStroke = highlightStroke;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
}