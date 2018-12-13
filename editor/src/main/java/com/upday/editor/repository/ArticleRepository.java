package com.upday.editor.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.upday.editor.dao.entity.ArticleEntity;


public interface ArticleRepository extends CrudRepository<ArticleEntity, String>, JpaSpecificationExecutor<ArticleEntity> {
	
	@Query("Select  article from ArticleEntity article where article.author=:author")
	public List<ArticleEntity> findArticlesByAuthor(@Param("author") String author);
	
	@Query("Select  article from ArticleEntity article where article.keywords like:keywords")
	public List<ArticleEntity> findArticlesbyKeywords(@Param("keywords") String keywords);
	
	
	Page<ArticleEntity> findAll(Pageable pageable);
	

}
