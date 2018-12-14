package com.upday.editor.service;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.dto.ArticleDto;
import com.upday.editor.model.Article;

@Test
public class EditorServiceBaseTest extends AbstractTestNGSpringContextTests {

	@DataProvider(name= "ArticleModel")
	public Object[][] ArticleModel() {
		Article article = Article.builder().articleUUID("123456").author("xyz").
				description("test article").header("xyz").keywords("xyz").build();
		
		return new Object[][]{{article}};
	}
	
	
	@DataProvider(name= "ArticleEntity")
	public Object[][] ArticleEntity() {
		ArticleEntity article = ArticleEntity.builder().articleUUID("123456").author("xyz").
				description("test article").header("xyz").keywords("xyz").build();
		
		return new Object[][]{{article}};
	}
	
	
	@DataProvider(name= "ArticleDto")
	public Object[][] ArticleDtoWithData() {
		ArticleDto article = ArticleDto.builder().author("xyz").
				description("test article").header("xyz").keywords("xyz").build();
		
		return new Object[][]{{article}};
	}

}
