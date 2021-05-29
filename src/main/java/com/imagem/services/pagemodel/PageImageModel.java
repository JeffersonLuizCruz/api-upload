package com.imagem.services.pagemodel;

import java.util.Map;

import org.springframework.data.domain.PageRequest;

public class PageImageModel {
	
	private int page = 0; 
	private int size = 10; 
	private String search = "";
	
	public PageImageModel(Map<String, String> params) {
		
		if(params.containsKey("page")) page = Integer.parseInt(params.get("page"));
		if(params.containsKey("size")) size = Integer.parseInt(params.get("size"));
		if(params.containsKey("search")) search = params.get("search");
	}
	
	public PageRequest toSpringPageRequest() {
		
		return PageRequest.of(page, size);
	}
	

	public PageImageModel() {
	}

	public PageImageModel(int page, int size, String search) {
		this.page = page;
		this.size = size;
		this.search = search;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}


	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}
