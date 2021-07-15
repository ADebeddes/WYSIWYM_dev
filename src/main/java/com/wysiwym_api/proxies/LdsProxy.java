package com.wysiwym_api.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wysiwym_api.beans.SimilarityParametersBean;
import com.wysiwym_api.beans.SimilarityResultBean;

@FeignClient(name = "lds", url = "localhost:9001")
//@RibbonClient(name ="lds")
public interface LdsProxy {
	@PostMapping(value = "/similarity", consumes = "application/json", produces = "application/json")
	@ResponseBody
	SimilarityResultBean similarity(@RequestBody SimilarityParametersBean params) throws Exception;
	
}
