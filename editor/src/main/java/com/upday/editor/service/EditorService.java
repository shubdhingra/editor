package com.upday.editor.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.upday.editor.domain.ArticleResource;
import com.upday.editor.dto.ArticleDto;

@Transactional
@Service
public interface EditorService {
	
	public ArticleResource createArticle(ArticleDto article);
	
	public List<ArticleResource> getArticles(String param);
	
	public ArticleResource getArticleById(String articleUUID);
	
	public ArticleResource updateArticle(ArticleDto article, String articleUUID, String ifMatch);
	
	public void deleteArticle(String articleUUID);

	public Page<ArticleResource> getArticlesbySpec(String author, String keywords, String fromDate, String toDate, Pageable pageable);

}
