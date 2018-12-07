package com.upday.editor.util;

import java.util.UUID;

import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.model.Article;

public class EditorUtil {
	
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
public static ArticleEntity getArticleEntity(Article article){
		
		ArticleEntity articleEntity = new ArticleEntity();
		articleEntity.setArticleUUID(getUUID());
		articleEntity.setAuthor(article.getAuthor());
		articleEntity.setDescription(article.getDescription());
		articleEntity.setHeader(article.getHeader());
		articleEntity.setKeywords(article.getKeywords());
		articleEntity.setPublishDate(article.getPublishDate());
		articleEntity.setText(article.getText());
		
		return null;
	}

}
