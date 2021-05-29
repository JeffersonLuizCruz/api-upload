package com.imagem.services;

import org.springframework.web.multipart.MultipartFile;

import com.imagem.entities.Image;
import com.imagem.services.pagemodel.PageImageModel;
import com.imagem.services.pagemodel.PageModel;

public interface ImageService {
	
	Image save(MultipartFile image);
	Image findById(Long id);
	PageModel<Image> listAllByOnLazyModel(PageImageModel pr);

}
