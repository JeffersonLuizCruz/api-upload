package com.imagem.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Image implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fileName; 
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime date;
	private byte[] data;
	
	private String type;
	
	
	
	public Image(String fileName2, String type2, byte[] data2) {
	}
	
	public Image(Long id, String fileName, LocalDateTime date, byte[] data, String type) {
		this.id = id;
		this.fileName = fileName;
		this.date = date;
		this.data = data;
		this.type = type;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@PrePersist
	protected void onCreate() {
		this.date = LocalDateTime.now();
	}
	
	public static class ImageBuilder{
		private String fileName;
		private String type;
		private byte[] data;
		
		private ImageBuilder(byte[] data) {
			this.data = data;
		}
		
		public static ImageBuilder newBuilder(byte[] data) {
			return new ImageBuilder(data);
		}
		
		public ImageBuilder withFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}
		
		public ImageBuilder withType(String type) {
			this.type = type;
			return this;
		}
		
		public Image build() {
			return new Image(fileName, type, data);
		}
	}
	
}
