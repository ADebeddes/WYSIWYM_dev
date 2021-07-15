package com.wysiwym_api.beans;

/**
 * 
 * @author Alexandre DEBEDDES
 * 
 */
import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimilarityResultBean {
	private String status;
	
	private HttpStatus code;
	
	@JsonProperty(value = "data", required = true)
	private ArrayList<ResultBean> data;
	
	private String message;
	
	public SimilarityResultBean() {}
	
	public SimilarityResultBean(HttpStatus code,String message) {
		this.code=code;
		this.data=null;
		this.message=message;
	}
	
	public void setStatus(String status) {
		this.status=status;
	}
	
	public void setCode(HttpStatus code) {
		this.code=code;
	}
	
	public void setData(ArrayList<ResultBean> data) {
		this.data=data;
	}
	
	public void setMessage(String message) {
		this.message=message;
	}
	
	public String getStatus() {
		return status;
	}
	
	public HttpStatus getCode() {
		return code;
	}
	
	public ArrayList<ResultBean> getData() {
		return data;
	}
	
	public String getMessage() {
		return message;
	}
	
}
