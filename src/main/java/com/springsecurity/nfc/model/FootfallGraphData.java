package com.springsecurity.nfc.model;

public class FootfallGraphData {
	private String[] labels;
	private DataSetsFootfall[] datasets = new DataSetsFootfall[1];
	
	public FootfallGraphData(String[] labels, DataSetsFootfall datasets) {
		this.labels = labels;
		this.datasets[0] = datasets;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public DataSetsFootfall[] getDatasets() {
		return datasets;
	}

	public void setDatasets(DataSetsFootfall[] datasets) {
		this.datasets = datasets;
	}
}
