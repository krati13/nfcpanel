package com.springsecurity.nfc.model;

public class TxnGraphData {
	private String[] labels;
	private DataSetsTxn[] datasets = new DataSetsTxn[1];
	
	public TxnGraphData(String[] labels, DataSetsTxn datasets) {
		this.labels = labels;
		this.datasets[0] = datasets;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public DataSetsTxn[] getDatasets() {
		return datasets;
	}

	public void setDatasets(DataSetsTxn[] datasets) {
		this.datasets = datasets;
	}
	
}