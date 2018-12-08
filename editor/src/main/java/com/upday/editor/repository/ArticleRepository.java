package com.upday.editor.repository;

import org.springframework.stereotype.Repository;

import com.upday.editor.dao.entity.ArticleEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {
	
	@Query("Select  article from ArticleEntity article where article.author=:author")
	public List<ArticleEntity> findArticlesByAuthor(@Param("author") String author);
	
	@Query("Select  article from ArticleEntity article where article.keywords like:keywords")
	public List<ArticleEntity> findArticlesbyKeywords(@Param("keywords") String keywords);
	

}
