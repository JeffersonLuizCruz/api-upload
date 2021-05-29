package com.imagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.imagem.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
	
	byte[] findByType(MultipartFile type);

}
