package com.upday.editor.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.upday.editor.dao.entity.ArticleEntity;

/**
 * Article Repository
 * @author Shubham Dhingra
 *
 */
public interface ArticleRepository extends CrudRepository<ArticleEntity, String>, JpaSpecificationExecutor<ArticleEntity> {

	/**
	 * gives the article on basis of header and Author
	 * @param header
	 * @param Author
	 * @return {@link ArticleEntity}
	 * 
	 */
	public ArticleEntity getByHeaderAndAuthor(@Param("header") String header, 
			@Param("author") String author);
	
	/**
	 * gives the pageable list of articlea 
	 * @param Pageable
	 * @return Page{@link ArticleEntity}
	 * 
	 */
	Page<ArticleEntity> findAll(Pageable pageable);
	

}
