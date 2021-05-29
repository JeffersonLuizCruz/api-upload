package com.imagem.services.impl;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imagem.entities.Image;
import com.imagem.repositories.ImageRepository;
import com.imagem.services.ImageService;
import com.imagem.services.exceptions.BadRequestException;
import com.imagem.services.exceptions.NotFoundException;
import com.imagem.services.pagemodel.PageImageModel;
import com.imagem.services.pagemodel.PageModel;
import com.imagem.specification.ImageSpecification;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired private ImageRepository imageRepository;
	
	
	//Diretório raiz.
	@Value("${contato.disco.raiz}")
	private String rootPath;
	//Diretório onde a imagem será salva.
	@Value("${contato.disco.diretorio-fotos}")
	private String photoDirectory;
	
	
	
	//Salva imagem no banco de dados Postgres.
	@Override
	public Image save(MultipartFile image) {
		try {
			return uploadImageToDataBase(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//Salva imagem no diretório.
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
	
	//Busca imagem por id .
	@Override
	public Image findById(Long id) {
		
		return verifyIfExist(id);
	}
	
	//Consulta paginada e consulta por extensão de arquivo e nome.
	@Override
	public PageModel<Image> listAllByOnLazyModel(PageImageModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Specification<Image> spec = ImageSpecification.search(pr.getSearch());
		
		Page<Image> page = imageRepository.findAll(spec, pageable);
		
		PageModel<Image> pm = new PageModel<>(
				(int)page.getTotalElements(),
				page.getSize(),
				page.getTotalPages(),
				page.getContent());
		
		return pm;
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
	//Validação por extensão de arquivo.
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
