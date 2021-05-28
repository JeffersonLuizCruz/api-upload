package com.imagem.services.impl;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imagem.entities.Image;
import com.imagem.entities.Image.ImageBuilder;
import com.imagem.repositories.ImageRepository;
import com.imagem.services.ImageService;
import com.imagem.services.exceptions.BadRequestException;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired private ImageRepository imageRepository;

	@Override
	public Image save(MultipartFile image) {
		try {
			return uploadImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Image uploadImage(MultipartFile image) throws IOException {
		byte[] data = image.getBytes();
		String type = image.getContentType();
		String fileName = image.getOriginalFilename();
		
		validateImageName(fileName);
		
		Image images = imageRepository.saveAndFlush(ImageBuilder.newBuilder(data)
												  				.withType(type)
												  				.withFileName(fileName)
												  				.build());
		return images;
	}
	
	private void validateImageName(String fileName) {
		if(!fileName.endsWith(".jpg") && !fileName.endsWith(".png") && !fileName.endsWith(".pdf") && !fileName.endsWith("tiff"))
			throw new BadRequestException("Extensão de imagem inválida!["+ fileName + "]");
	}

}
