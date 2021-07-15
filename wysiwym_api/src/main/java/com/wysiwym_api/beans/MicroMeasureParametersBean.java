package com.wysiwym_api.beans;

import java.util.List;

public class MicroMeasureParametersBean {

	private LdDatasetMainBean ldDatasetMain;
	private List<MicroMeasureResourcesBean> microMeasureResourcesBean;
	private MicroMeasureOptionsBean options;
	
	public MicroMeasureParametersBean() {
		
	}

	public LdDatasetMainBean getLdDatasetMain() {
		return ldDatasetMain;
	}
	
	public void setLdDatasetMain(LdDatasetMainBean ldDatasetMain) {
		this.ldDatasetMain = ldDatasetMain;
	}
	
	public List<MicroMeasureResourcesBean> getResources() {
		return microMeasureResourcesBean;
	}

	public void setResources(List<MicroMeasureResourcesBean> microMeasureResourcesBean) {
		this.microMeasureResourcesBean = microMeasureResourcesBean;
	}
	
	public MicroMeasureOptionsBean getOptions() {
		return options;
	}

	public void setProperty(MicroMeasureOptionsBean options) {
		this.options = options;
	}
	
}
