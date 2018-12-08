package com.upday.editor.util;

import java.util.UUID;

import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.model.Article;
import com.upday.editor.model.SearchCriteria;

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


public static SearchCriteria getSearchCriteria(String criteria) {
	SearchCriteria sc = new SearchCriteria();
	String [] filter = null;
	if(criteria !=null) {
		if(criteria.contains(":")) {
			filter = criteria.split(":");
			sc.setKey(filter[0]);
			sc.setValue(filter[1]);
		}
	}
	return sc;
	
}

}
