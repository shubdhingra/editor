package com.upday.editor.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.upday.editor.dao.entity.ArticleEntity;

public interface ArticleDao {
	
	
	public ArticleEntity createArticle(ArticleEntity article);
	
	public ArticleEntity updateArticle(ArticleEntity article);
	public List<ArticleEntity> getArticles();
	public ArticleEntity getArticleByUUID(String articleUUID);
	
	public void deleteArticle(ArticleEntity article);
	public List<ArticleEntity> getArticlesByKeywords(String keywords);
	public ArticleEntity getArticleByHeaderAndAuthor(String header, String author);

	List<ArticleEntity> getArticlesByAuthor(String author);
	List<ArticleEntity> getArticlesbySpec(Specification<ArticleEntity> spec);

}
