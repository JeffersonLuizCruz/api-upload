package com.imagem.controllers.exceptions;

import java.io.Serializable;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	private String message;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private OffsetDateTime date;
	
	

	public ApiError() {
	}

	public ApiError(Integer code, String message, OffsetDateTime date) {
		this.code = code;
		this.message = message;
		this.date = date;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OffsetDateTime getDate() {
		return date;
	}

	public void setDate(OffsetDateTime date) {
		this.date = date;
	}
	

}
