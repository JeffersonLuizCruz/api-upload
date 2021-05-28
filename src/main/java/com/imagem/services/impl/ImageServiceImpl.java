package com.imagem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imagem.entities.Image;
import com.imagem.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired private ImageRepository imageRepository;

	@Override
	public Image save(MultipartFile image) {
		return null;
	}

}
