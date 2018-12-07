package com.upday.editor.dao;

import java.util.List;

import com.upday.editor.dao.entity.ArticleEntity;

public interface ArticleDao {
	
	
	public ArticleEntity createArticle(ArticleEntity article);
	
	public ArticleEntity updateArticle(ArticleEntity article);
	public List<ArticleEntity> getArticles();
	public ArticleEntity getArticleByUUID(String articleUUID);
	
	public void deleteArticle(ArticleEntity article);

}
