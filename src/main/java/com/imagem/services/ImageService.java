package com.imagem.services;

import org.springframework.web.multipart.MultipartFile;

import com.imagem.entities.Image;

public interface ImageService {
	
	Image save(MultipartFile image);

}
