package com.upday.editor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.dto.ArticleDto;
import com.upday.editor.model.Article;
import com.upday.editor.domain.ArticleResource;

@Test
public class EditorServiceBaseTest extends AbstractTestNGSpringContextTests {

	@DataProvider(name= "articleModel")
	public Object[][] articleModel() {
		Article article = Article.builder().articleUUID("123456").author("xyz").
				description("test article").header("xyz").keywords("xyz").build();
		
		return new Object[][]{{article}};
	}
	
	@DataProvider(name= "articleEntity")
	public Object[][] articleEntity() {
		ArticleEntity article = ArticleEntity.builder().articleUUID("123456").author("xyz").
				description("test article").header("xyz").keywords("xyz").build();
		
		return new Object[][]{{article}};
	}
	
	
	@DataProvider(name= "articleDto")
	public Object[][] articleDto() {
		ArticleDto article = ArticleDto.builder().author("xyz").
				description("test article").header("xyz").keywords("xyz").build();
		
		return new Object[][]{{article}};
	}
	
	@DataProvider(name = "articleResource")
	public Object[][] articleResource(){
		ArticleResource article = ArticleResource.builder().author("xyz").
				description("test article").header("xyz").keywords("xyz").build();
		
		return new Object[][]{{article}};
	}
	
	@DataProvider(name = "articlesList")
	public Object[][] articlesList() {
		List<ArticleEntity> articles = new ArrayList<>();
		ArticleEntity article1 = ArticleEntity.builder().articleUUID("123456").author("xyz").
				description("test article").header("xyz").keywords("xyz").build();
		ArticleEntity article2 = ArticleEntity.builder().articleUUID("85858").author("abc").
				description("abc").header("abc").keywords("abc").build();
		
		articles.add(article1);
		articles.add(article2);
		
		return new Object[][]{{articles}};
		
	}

}
