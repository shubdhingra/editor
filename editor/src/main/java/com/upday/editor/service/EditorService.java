package com.upday.editor.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.upday.editor.domain.ArticleResource;
import com.upday.editor.dto.ArticleDto;
/**
 * Editor Service interface
 * @author Shubham Dhingra
 *
 */
@Transactional
@Service
public interface EditorService {
	
	public ArticleResource createArticle(ArticleDto article);
	
	public ArticleResource getArticleById(String articleUUID);
	
	public ArticleResource updateArticle(ArticleDto article, String articleUUID, String ifMatch);
	
	public void deleteArticle(String articleUUID);

	public Page<ArticleResource> getArticles(String author, String keywords, Pageable pageable);

}
