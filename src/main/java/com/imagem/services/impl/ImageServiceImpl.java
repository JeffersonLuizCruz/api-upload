package com.imagem.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imagem.entities.Image;
import com.imagem.repositories.ImageRepository;
import com.imagem.services.ImageService;
import com.imagem.services.exceptions.BadRequestException;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired private ImageRepository imageRepository;

	@Override
	public Image save(MultipartFile image) {
		uploadImage(image);
		return null;
	}

	private void uploadImage(MultipartFile image) {
		String type = image.getContentType();
		String fileName = image.getOriginalFilename();
		
		validateImageName(fileName);
	}
	
	private void validateImageName(String fileName) {
		if(!fileName.endsWith(".jpg") && !fileName.endsWith(".png") && !fileName.endsWith(".pdf") && !fileName.endsWith("tiff"))
			throw new BadRequestException("Extensão de imagem inválida!["+ fileName + "]");
	}

}
