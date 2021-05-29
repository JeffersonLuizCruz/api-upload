package com.imagem.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.imagem.entities.Image;

public class ImageSpecification {
	
	public static Specification<Image> search(String text){
		
		return new Specification<Image>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Image> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				if(text == null || text.trim().length() <= 0) return null;
				
				String likeTerm = "%" + text + "%";
				
				Predicate predicate = cb.or(cb.like(root.get("type"), likeTerm),
						      cb.or(cb.like(root.get("fileName"), likeTerm),
						      cb.or(cb.like(root.get("fileName").as(String.class), likeTerm))));
									  
				return predicate;
			}
			
			
		};
	}

}
