package com.wysiwym_api.model;

/**
 * 
 * @author Alexandre DEBEDDES
 *
 */
public class LdDatasetMain {
	private String name;
	private Prefixes prefixes;
	private String link;
	private String defaultGraph;
	private String baseResourceURL;
	
	public LdDatasetMain() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Prefixes getPrefixes() {
		return prefixes;
	}
	public void setPrefixes(Prefixes prefixes) {
		this.prefixes = prefixes;
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
		return "LdDatasetMain [name=" + name + ", prefixes=" + prefixes.toString() + ", link=" + link + ", defaultGraph="
				+ defaultGraph + ", baseResourceURL=" + baseResourceURL + "]";
	}
	
}
