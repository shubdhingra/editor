package com.upday.editor.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.upday.editor.domain.ArticleResource;
import com.upday.editor.dto.ArticleDto;

@Transactional
@Service
public interface ArticleService {
	
	public ArticleResource createArticle(ArticleDto article);
	
	public List<ArticleResource> getArticles(String param);
	
	public ArticleResource getArticleById(String articleUUID);
	
	public ArticleResource updateArticle(ArticleDto article, String articleUUID, String ifMatch);
	
	public void deleteArticle(String articleUUID);

}
