package com.upday.editor.util;

import java.util.UUID;

import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.model.Article;
import com.upday.editor.model.SearchCriteria;

/**
 * Utility class for providing various util methods
 * 
 * @author Shubham Dhingra
 *
 */
public class EditorUtil {
	/**
	 * generated the UUID for every article
	 * 
	 * @return UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * conversion method
	 * @param {@link Article}
	 * @return {@link ArticleEntity}
	 */
	public static ArticleEntity getArticleEntity(Article article) {

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
