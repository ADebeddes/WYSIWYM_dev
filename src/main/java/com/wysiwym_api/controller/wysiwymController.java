package com.wysiwym_api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wysiwym_api.beans.MeasuresResultBean;
import com.wysiwym_api.beans.MicroMeasureParametersBean;
import com.wysiwym_api.beans.MicroMeasureResultBean;
import com.wysiwym_api.beans.SimilarityParametersBean;
import com.wysiwym_api.beans.SimilarityResultBean;
import com.wysiwym_api.proxies.LdsProxy;
import com.wysiwym_api.proxies.MeasuresProxy;

@Controller
public class wysiwymController {

	@Autowired
	LdsProxy ldsProxy;
	
	@Autowired
	MeasuresProxy measuresProxy;
	
	@PostMapping(value = "/similarity", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public SimilarityResultBean lds(Model model,@RequestBody SimilarityParametersBean params) throws Exception {
		SimilarityResultBean res =ldsProxy.similarity(params);
		return res;
		
	}
	
	@PostMapping(value = "/measures/getMeasures",produces ="application/json")
	@ResponseBody
	public ArrayList<MeasuresResultBean> getMeasures() throws Exception {
		ArrayList<MeasuresResultBean> mesures =measuresProxy.getMeasures();
		return mesures;
	}
	
	@PostMapping(value = "/measures/getMicroMeasures",produces ="application/json")
	@ResponseBody
	public ArrayList<MeasuresResultBean> getMicroMeasures() throws Exception {
		ArrayList<MeasuresResultBean> mesures =measuresProxy.getMicroMeasures();
		for(MeasuresResultBean m:mesures){
			System.out.println(m);
		}
		return mesures;
	}
	
	@PostMapping(value = "measures/microMeasure", consumes = "application/json",produces ="application/json")
	@ResponseBody
	public MicroMeasureResultBean microMeasure(@RequestBody MicroMeasureParametersBean params) throws Exception{
		MicroMeasureResultBean res =measuresProxy.microMeasure(params);
		return res;
	}
}
