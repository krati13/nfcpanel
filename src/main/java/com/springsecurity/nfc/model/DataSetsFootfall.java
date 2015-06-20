package com.springsecurity.nfc.model;

public class DataSetsFootfall {
	private String label;
	private String fillColor;
	private String strokeColor;
	private String pointColor;
	private String pointStrokeColor;
	private String pointHighlightFill;
	private String pointHighlightStroke;
	private String[] data;
	
	public DataSetsFootfall(String[] data) {
		this.label = "Footfall of this year";
		this.fillColor = "rgba(220,220,220,0.2)";
		this.strokeColor = "rgba(220,220,220,1)";
		this.pointColor = "rgba(220,220,220,1)";
		this.pointStrokeColor = "#fff";
		this.pointHighlightFill = "#fff";
		this.pointHighlightStroke = "rgba(220,220,220,1)";
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
	public String getPointColor() {
		return pointColor;
	}
	public void setPointColor(String pointColor) {
		this.pointColor = pointColor;
	}
	public String getPointStrokeColor() {
		return pointStrokeColor;
	}
	public void setPointStrokeColor(String pointStrokeColor) {
		this.pointStrokeColor = pointStrokeColor;
	}
	public String getPointHighlightFill() {
		return pointHighlightFill;
	}
	public void setPointHighlightFill(String pointHighlightFill) {
		this.pointHighlightFill = pointHighlightFill;
	}
	public String getPointHighlightStroke() {
		return pointHighlightStroke;
	}
	public void setPointHighlightStroke(String pointHighlightStroke) {
		this.pointHighlightStroke = pointHighlightStroke;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
}
