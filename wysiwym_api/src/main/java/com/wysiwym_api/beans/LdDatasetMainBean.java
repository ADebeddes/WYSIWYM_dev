package com.wysiwym_api.beans;

/**
 * 
 * @author Alexandre DEBEDDES
 *
 */
public class LdDatasetMainBean {
	private String name;
	private PrefixesBean prefixesBean;
	private String link;
	private String defaultGraph;
	private String baseResourceURL;
	
	public LdDatasetMainBean() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PrefixesBean getPrefixes() {
		return prefixesBean;
	}
	public void setPrefixes(PrefixesBean prefixesBean) {
		this.prefixesBean = prefixesBean;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDefaultGraph() {
		return defaultGraph;
	}
	public void setDefaultGraph(String defaultGraph) {
		this.defaultGraph = defaultGraph;
	}
	public String getBaseResourceURL() {
		return baseResourceURL;
	}
	public void setBaseResourceURL(String baseResourceURL) {
		this.baseResourceURL = baseResourceURL;
	}

	@Override
	public String toString() {
		return "LdDatasetMain [name=" + name + ", prefixes=" + prefixesBean.toString() + ", link=" + link + ", defaultGraph="
				+ defaultGraph + ", baseResourceURL=" + baseResourceURL + "]";
	}
	
}
