package com.wysiwym_api.proxies;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wysiwym_api.beans.MeasuresResultBean;
import com.wysiwym_api.beans.MicroMeasureParametersBean;
import com.wysiwym_api.beans.MicroMeasureResultBean;

@FeignClient(name = "measures", url = "localhost:9002")
public interface MeasuresProxy {
	@PostMapping(value = "/getMeasures",produces ="application/json")
	ArrayList<MeasuresResultBean> getMeasures() throws Exception;
	
	@PostMapping(value = "/getMicroMeasures",produces ="application/json")
	public ArrayList<MeasuresResultBean> getMicroMeasures() throws Exception;
	
	@PostMapping(value = "/microMeasure", consumes = "application/json",produces ="application/json")
	@ResponseBody
	public MicroMeasureResultBean microMeasure(@RequestBody MicroMeasureParametersBean params) throws Exception;
}
