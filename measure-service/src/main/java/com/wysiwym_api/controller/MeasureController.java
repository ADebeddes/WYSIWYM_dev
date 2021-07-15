package com.wysiwym_api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wysiwym_api.model.MeasuresResult;
import com.wysiwym_api.model.MicroMeasureParameters;
import com.wysiwym_api.model.MicroMeasureResult;
import com.wysiwym_api.model.MicroMeasures;
import com.wysiwym_api.service.MeasureService;

import lds.measures.Measure;

@RestController
/***
 * 
 * @author Alexandre DEBEDDES
 *
 */
public class MeasureController {

	@Autowired
    private MeasureService measureService;
	
	/*@Autowired
    ApplicationPropertiesConfiguration appProperties;*/
	
	@PostMapping(value = "/getMeasures",produces ="application/json")
	public ArrayList<MeasuresResult> getMeasures() throws Exception{
		ArrayList<MeasuresResult> res = new ArrayList<MeasuresResult>();
		for (Measure measure : Measure.values()) {
			MeasuresResult m = new MeasuresResult();
			m.setName(Measure.getName(measure));
			m.setAttribute(Measure.getName(measure));
		    res.add(m);
		}
		return res;
	}
	
	@PostMapping(value = "/getMicroMeasures",produces ="application/json")
	public ArrayList<MeasuresResult> getMicroMeasures() throws Exception{
		ArrayList<MeasuresResult> res = new ArrayList<MeasuresResult>();
		for (MicroMeasures measure : MicroMeasures.values()) {
			MeasuresResult m = new MeasuresResult();
			m.setAttribute(MicroMeasures.getName(measure));
			m.setName(MicroMeasures.getPath(measure));
			m.setDescription(MicroMeasures.getDescription(measure));
		    res.add(m);
		}
		return res;
	}
	
	@PostMapping(value = "/microMeasure", consumes = "application/json",produces ="application/json")
	@ResponseBody
	public MicroMeasureResult microMeasure(@RequestBody MicroMeasureParameters params) throws Exception{
		MicroMeasureResult res = new MicroMeasureResult();
		try {
			res = measureService.newMeasure(params);
			if(res.getMessage()!=null) {
				res.setStatus("error");
			}
			else {
				res.setStatus("success");
				res.setCode(HttpStatus.OK);
			}
		} 
		catch (Exception e) {
			res = new MicroMeasureResult();
			res.setStatus("error");
			res.setMessage("An error has occured : " + e.getMessage());
		}
		return res;
	}
	
}
