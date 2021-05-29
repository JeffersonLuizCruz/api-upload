package com.imagem.controllers.exceptions;

import java.time.OffsetDateTime;
import java.util.List;

public class ApiErrorList extends ApiError{
	private static final long serialVersionUID = 1L;

	private List<String> erros;
	
	

	public ApiErrorList() {
	}

	public ApiErrorList(Integer code, String message, OffsetDateTime date, List<String> erros) {
		super(code, message, date);
		this.erros = erros;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}
	
	
	
}
