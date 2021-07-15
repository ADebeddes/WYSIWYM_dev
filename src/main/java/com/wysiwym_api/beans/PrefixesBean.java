package com.wysiwym_api.beans;

/**
 * 
 * @author Alexandre DEBEDDES
 *
 */
public class PrefixesBean {
	private NsPrefixMapBean nsPrefixMapBean;
	
	public PrefixesBean() {}
	
	public NsPrefixMapBean getNsPrefixMap() {
		return nsPrefixMapBean;
	}
	public void setNsPrefixMap(NsPrefixMapBean nsPrefixMapBean) {
		this.nsPrefixMapBean = nsPrefixMapBean;
	}

	@Override
	public String toString() {
		return "Prefixes [nsPrefixMap=" + nsPrefixMapBean.toString() + "]";
	}
	
}
