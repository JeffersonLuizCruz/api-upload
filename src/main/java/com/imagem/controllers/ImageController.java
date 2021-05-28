package com.imagem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imagem.entities.Image;
import com.imagem.services.impl.ImageServiceImpl;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
	
	@Autowired private ImageServiceImpl imageService;
	
	@PostMapping
	public ResponseEntity<Image> updateImage(@RequestParam MultipartFile image){
		Image images = imageService.save(image);
		
		return ResponseEntity.ok().body(images);
	}

}
