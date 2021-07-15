package com.wysiwym_api.beans;

import java.util.List;

/**
 * 
 * @author Alexandre DEBEDDES
 *
 */
public class SimilarityParametersBean {
	
	private LdDatasetMainBean ldDatasetMain;
	private List<SimilarityResourcesBean> similarityResourcesBean;
	private SimilarityOptionsBean similarityOptionsBean;
	
	public SimilarityParametersBean() {}
	
	public void setLdDatasetMain(LdDatasetMainBean ldDatasetMain) {
		this.ldDatasetMain = ldDatasetMain;
	}
	
	public void setResources(List<SimilarityResourcesBean> similarityResourcesBean) {
		this.similarityResourcesBean = similarityResourcesBean;
	}
	
	public void setOptions(SimilarityOptionsBean similarityOptionsBean) {
		this.similarityOptionsBean =similarityOptionsBean;
	}
	
	public LdDatasetMainBean getLdDatasetMain() {
		return ldDatasetMain;
	}
	
	public List<SimilarityResourcesBean> getResources() {
		return similarityResourcesBean;
	}
	
	public SimilarityOptionsBean getOptions() {
		return similarityOptionsBean;
	}

	@Override
	public String toString() {
		return "SimilarityParameters [ldDatasetMain=" + ldDatasetMain + ", resources=" + similarityResourcesBean + ", options=" + similarityOptionsBean + "]";
	}
	
	
}
