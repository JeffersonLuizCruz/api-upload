package com.imagem.controllers;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imagem.entities.Image;
import com.imagem.services.impl.ImageServiceImpl;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
	
	@Autowired private ImageServiceImpl imageService;
	
	private static  String PATH = "fotos";
	
	
	@PostMapping
	public ResponseEntity<Image> updateImage(@RequestParam MultipartFile image) throws IOException{
		Image images = imageService.save(image);
		imageService.savePhotoToDirectory(PATH, image);
		
		return ResponseEntity.ok().body(images);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Image findType(@PathVariable Long id){
		
		return imageService.findById(id);
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id){
		Image doc = imageService.findById(id);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getFileName()+"\"")
				.body(new ByteArrayResource(doc.getBytes()));
	}

}
