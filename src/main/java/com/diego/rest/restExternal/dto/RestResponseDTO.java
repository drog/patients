package com.diego.rest.restExternal.dto;

import java.util.Arrays;

public class RestResponseDTO {

	private Boolean success;
	
	private String errors;
	
	private Integer total;
	
	private Object data;

	
	
	
	public RestResponseDTO(Boolean success, String errors, Integer total, Object data) {
		super();
		this.success = success;
		this.errors = errors;
		this.total = total;
		this.data = data;
	}

	public RestResponseDTO(Object data) {
		super();
		this.success = Boolean.TRUE;
		this.errors = null;
		this.total = 0;
		this.data = Arrays.asList(data);;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
