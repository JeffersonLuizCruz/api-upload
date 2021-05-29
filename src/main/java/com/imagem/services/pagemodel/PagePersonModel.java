package com.imagem.services.pagemodel;

import java.io.Serializable;

public class PagePersonModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int page; 
	private int size;
	
	
	public PagePersonModel() {
	}

	public PagePersonModel(int page, int size) {
		this.page = page;
		this.size = size;
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
	
}
