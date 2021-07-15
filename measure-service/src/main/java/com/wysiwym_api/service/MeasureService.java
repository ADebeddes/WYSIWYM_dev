package com.wysiwym_api.service;

import lombok.Data;
import slib.sml.sm.core.measures.vector.CosineSimilarity;


import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.shared.impl.PrefixMappingImpl;
import org.springframework.stereotype.Service;

import com.wysiwym_api.model.MicroMeasureParameters;
import com.wysiwym_api.model.MicroMeasureResources;
import com.wysiwym_api.model.MicroMeasureResult;
import com.wysiwym_api.model.Result;

import info.debatty.java.stringsimilarity.Damerau;
import info.debatty.java.stringsimilarity.Jaccard;
import info.debatty.java.stringsimilarity.JaroWinkler;
import info.debatty.java.stringsimilarity.Levenshtein;
import info.debatty.java.stringsimilarity.LongestCommonSubsequence;
import info.debatty.java.stringsimilarity.NGram;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
import info.debatty.java.stringsimilarity.OptimalStringAlignment;
import info.debatty.java.stringsimilarity.QGram;
import info.debatty.java.stringsimilarity.RatcliffObershelp;
import info.debatty.java.stringsimilarity.SorensenDice;
import info.debatty.java.stringsimilarity.interfaces.StringDistance;
//import info.debatty.java.stringsimilarity.*;
import ldq.LdDataset;
import ldq.LdDatasetFactory;
import lds.LdManager.ontologies.Ontology;
import lds.resource.R;

/***
 * 
 * @author Alexandre DEBEDDES
 *
 */

@Data
@Service
public class MeasureService {

	public MicroMeasureResult newMeasure(MicroMeasureParameters params) throws Exception{
		//JaroWinkler l = new JaroWinkler();
		MicroMeasureResult simRes = new MicroMeasureResult();
		ArrayList<Result> data = new ArrayList<Result>();

		ArrayList<String> edges1 = new ArrayList<String>();
		ArrayList<String> edges2 = new ArrayList<String>();

		PrefixMapping prefixes = new PrefixMappingImpl();

		prefixes.setNsPrefix("xsd", params.getLdDatasetMain().getPrefixes().getNsPrefixMap().getXsd());
		prefixes.setNsPrefix("rdfs", params.getLdDatasetMain().getPrefixes().getNsPrefixMap().getRdfs());
		prefixes.setNsPrefix("dbpedia", params.getLdDatasetMain().getPrefixes().getNsPrefixMap().getDbpedia());
		prefixes.setNsPrefix("dbo", params.getLdDatasetMain().getPrefixes().getNsPrefixMap().getDbpediaowl());
		prefixes.setNsPrefix("rdf", params.getLdDatasetMain().getPrefixes().getNsPrefixMap().getRdf());

		LdDataset dataSetMain = LdDatasetFactory.getInstance().service(params.getLdDatasetMain().getLink()).name(params.getLdDatasetMain().getName()).defaultGraph(params.getLdDatasetMain().getDefaultGraph()).prefixes(prefixes).create();

		Ontology.loadIndexes();

		for(MicroMeasureResources r: params.getResources()) {

			String sR1 = params.getLdDatasetMain().getBaseResourceURL()+r.getResource1();
			String sR2 = params.getLdDatasetMain().getBaseResourceURL()+r.getResource2();

			R r1 = new R(sR1);
			R r2 = new R(sR2);

			edges1 = (ArrayList<String>) getProperty(r1,dataSetMain,params.getResources().get(0).getProperty());
			edges2 = (ArrayList<String>) getProperty(r2,dataSetMain,params.getResources().get(0).getProperty());
			
			if(edges1 == null || edges2 == null) {
				simRes.setMessage("Unknown property");
				return simRes;
			}
			
			double score = 0.0;
			
			if(edges1.size() == 1 && edges2.size() == 1) {
				if(params.getOptions().getMeasureType().equals("numeric")) {
					String[] tab1 = edges1.get(0).split("\"");
					String[] tab2 = edges2.get(0).split("\"");
					score = IntMeasure(Double.parseDouble(tab1[1]),Double.parseDouble(tab2[1]),params.getOptions().getMeasureType());
				}
				else {
					score = StringMeasure(edges1.get(0),edges2.get(0),params.getOptions().getMeasureType());
				}
			}
			else 
				score = ListMeasure(edges1,edges2,params.getOptions().getMeasureType());
			Result res = new Result();
			res.setResource1(r.getResource1());
			res.setResource2(r.getResource2());
			res.setScore(score);

			data.add(res);
		}
		simRes.setData(data);
		return simRes;
	}

	public List<String> getProperty(R a, LdDataset dataSetMain, String request) {
		List<String> propertyList = new ArrayList<String>();
		
		String object , property;

		ParameterizedSparqlString query_cmd = dataSetMain.prepareQuery();

		query_cmd.setCommandText("select ?"+request+"\n"
				+ "from <" + dataSetMain.getDefaultGraph()+ "> \n"
				+ "where {<" + a.getUri() + "> <http://dbpedia.org/ontology/"+ request +"> ?"+request+" .}");

		ResultSet resultSet = dataSetMain.executeSelectQuery(query_cmd.toString());

		while (resultSet.hasNext()) {
			QuerySolution qs = resultSet.nextSolution();
			if(request.equals("abstract")) {
				if(qs.toString().contains("@en"))
					propertyList.add(qs.toString());
			}
			else {
				propertyList.add(qs.toString());
			}
		}
		dataSetMain.close();

		if(! propertyList.isEmpty())
			return propertyList;
		else {
			query_cmd.setCommandText("select distinct ?object ?property " + (dataSetMain.getDefaultGraph() == null ? ("") : "from <" + dataSetMain.getDefaultGraph()+ ">") + " where {<" + a.getUri() + "> ?property ?object ."
	                + " filter(isuri(?object)) }");


	        ResultSet resultSet1 = dataSetMain.executeSelectQuery(query_cmd.toString());

	        while (resultSet1.hasNext()) {
	        	QuerySolution qs = resultSet1.nextSolution();
	            object = Ontology.compressValue(qs.getResource("object"));
	            property = Ontology.compressValue(qs.getResource("property"));
	            if(property.contains(request))
	            	propertyList.add(object);
			}
	        if(! propertyList.isEmpty())
				return propertyList;
			else
				return null;
		}
	} 

	public double StringMeasure(String r1,String r2,String measure) {
		switch(measure) {
		case "levenshtein":
			Levenshtein  l = new Levenshtein ();
			return l.distance(r1, r2);
		case "normalizedLevenshtein":
			NormalizedLevenshtein  nl = new NormalizedLevenshtein ();
			return nl.distance(r1, r2);
		case "damerauLevenshtein ":
			Damerau d = new Damerau();
			return d.distance(r1, r2);
		case "optimalStringAligment":
			OptimalStringAlignment osa = new OptimalStringAlignment();
			return osa.distance(r1, r2);
		case "jaroWinkler":
			JaroWinkler jw = new JaroWinkler();
			return jw.distance(r1, r2);
		case "longestCommonSubsequence ":
			LongestCommonSubsequence lcs = new LongestCommonSubsequence();
			return lcs.distance(r1, r2);
		case "metricLongestCommonSubsequence":
			info.debatty.java.stringsimilarity.MetricLCS mlcs = 
            new info.debatty.java.stringsimilarity.MetricLCS();
			return mlcs.distance(r1, r2);
		case "nGram":
			NGram twogram = new NGram(2);
			return twogram.distance(r1, r2);
		case "qGram":
			QGram qGram =new QGram();
			return qGram.distance(r1, r2);
		case "cosineSimilarity":
			CosineSimilarity cSim =new CosineSimilarity();
			return ((StringDistance) cSim).distance(r1, r2);
		case "jaccardIndex":
			Jaccard jInd =new Jaccard();
			return jInd.distance(r1, r2);
		case "sorensenDiceCoefficient":
			SorensenDice sDice =new SorensenDice();
			return sDice.distance(r1, r2);
		case "ratcliffObershelp":
			RatcliffObershelp  ro =new RatcliffObershelp ();
			return ro.distance(r1, r2);
		default:
			return 0.0;
		}
	}
	
	public double IntMeasure(double r1, double r2, String measure) {
		double score = 0.0;
		if(r1<r2)
			score = (r2 - r1) / r2 * 100;
		else 
			score = (r1 - r2) / r1 * 100;
		return score/100;
	}
	
	private double ListMeasure(List<String> r1,List<String> r2,String measure) {
		boolean isIn = false;
		double match = 0;
		double mismatch = 0;
		for(String s1: r1) {
			
			for(String s2: r2) {
				if(s1.equals(s2)) {
					match++;
					isIn = true;
				}
			}
			if(!isIn) 
				mismatch++;
			else 
				isIn = false;
		}

		double score = match * 100 / (match+mismatch+((double)r2.size()-match));
		return score/100;
	}
}
