package com.imagem.services.impl;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imagem.entities.Image;
import com.imagem.repositories.ImageRepository;
import com.imagem.services.ImageService;
import com.imagem.services.exceptions.BadRequestException;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired private ImageRepository imageRepository;
	
	@Value("${contato.disco.raiz}")
	private String pathRoot ="/tmp/contato-disco";
	
	@Value("${contato.disco.diretorio-fotos}")
	private String pathPhoto = "fotos";

	@Override
	public Image save(MultipartFile image) {
		
		try {
			return uploadImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.salvar(this.pathPhoto, image);
		return null;
	}

	private Image uploadImage(MultipartFile image) throws IOException {
		String type = image.getContentType();
		String fileName = image.getOriginalFilename();
		
		validateImageName(fileName);
		
		Image images = new Image();
		images.setType(type);
		images.setFileName(fileName);
		images.setRootPath(pathRoot);
		images.setPath(pathPhoto);
		
		return imageRepository.save(images);
	}
	
	private void validateImageName(String fileName) {
		if(!fileName.endsWith(".jpg") && !fileName.endsWith(".png") && !fileName.endsWith(".pdf") && !fileName.endsWith("tiff"))
			throw new BadRequestException("Extensão de imagem inválida!["+ fileName + "] - Extensão de arquivo válido: jpeg, png, tiff e pdf");
	}
	
	private void salvar(String diretorio, MultipartFile arquivo) {
		String fileName = arquivo.getOriginalFilename();
		validateImageName(fileName);
		
		Path diretorioPath = Paths.get(this.pathPhoto, diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
		
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());			
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}		
	}

}
