package com.imagem.services.impl;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imagem.entities.Image;
import com.imagem.repositories.ImageRepository;
import com.imagem.services.ImageService;
import com.imagem.services.exceptions.BadRequestException;
import com.imagem.services.exceptions.NotFoundException;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired private ImageRepository imageRepository;
	
	
	
	
	@Value("${contato.disco.raiz}")
	private String rootPath;
	
	@Value("${contato.disco.diretorio-fotos}")
	private String photoDirectory;
	
	
	

	@Override
	public Image save(MultipartFile image) {
		
		try {
			return uploadImageToDataBase(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void savePhotoToDirectory(String directory, MultipartFile file) throws IOException {	
		Path directoryPath = Paths.get(this.rootPath, directory);
		Path filePath = directoryPath.resolve(file.getOriginalFilename());
		
		try {
			Files.createDirectories(directoryPath);
			file.transferTo(filePath.toFile());			
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}		
	}
	
	@Override
	public Image findById(Long id) {
		
		return verifyIfExist(id);
	}
	
	

	private Image uploadImageToDataBase(MultipartFile image) throws IOException {
		String type = image.getContentType();
		String fileName = image.getOriginalFilename();
		byte[] bytes = image.getBytes();
		
		validateExtension(fileName);
		
		Image images = new Image();
		
		images.setBytes(bytes);
		images.setType(type);
		images.setFileName(fileName);
		images.setRootPath(rootPath);
		images.setPath(photoDirectory);
		
		
		return imageRepository.saveAndFlush(images);
	}
	
	private void validateExtension(String fileName) {
		if(!fileName.endsWith(".jpg") && !fileName.endsWith(".png") && !fileName.endsWith(".pdf") && !fileName.endsWith("tiff"))
			throw new BadRequestException("Extensão de imagem inválida!["+ fileName + "] - Extensão de arquivo válido: jpeg, png, tiff e pdf");
	}
	
	private Image verifyIfExist(Long id) {
		Optional<Image> result = imageRepository.findById(id);
		result.orElseThrow(() -> new NotFoundException("Imagem com id ["+ id +"] não encontradada na Base de Dado"));
		
		return result.get();
	}

}
