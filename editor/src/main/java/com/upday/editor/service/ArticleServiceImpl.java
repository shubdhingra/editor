package com.upday.editor.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upday.editor.dao.ArticleDao;
import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.domain.ArticleResource;
import com.upday.editor.dto.ArticleDto;
import com.upday.editor.model.Article;
import com.upday.editor.util.EditorUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public String createArticle(ArticleDto article) {
		ArticleEntity articleEntity = modelMapper.map(article, ArticleEntity.class);
		articleEntity.setArticleUUID(EditorUtil.getUUID());
		log.debug("Creating new aapplication with article UUID :{}", articleEntity.getArticleUUID());
		ArticleEntity persistedArticleEntity = articleDao.createArticle(articleEntity);
		return persistedArticleEntity.getArticleUUID();
	}

	@Override
	public List<ArticleResource> getArticles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleResource getArticleById(String articleUUID) {
		ArticleEntity articleEntity = articleDao.getArticleByUUID(articleUUID);
		return modelMapper.map(articleEntity, ArticleResource.class);
	}

	@Override
	public ArticleResource updateArticle(ArticleDto article, String articleUUID) {
	//1. check whether article exists or not
		ArticleEntity articleEntity = articleDao.getArticleByUUID(articleUUID);
		ArticleEntity newArticleEntity = modelMapper.map(article, ArticleEntity.class);
		newArticleEntity.setArticleUUID(articleUUID);
		return modelMapper.map(articleDao.updateArticle(newArticleEntity), ArticleResource.class);
	}

	@Override
	public void deleteArticle(String articleUUID) {
		//1. check whether article exists or not
		ArticleEntity articleEntity = articleDao.getArticleByUUID(articleUUID);
		log.debug("Deleting the article with header {}", articleEntity.getHeader());
		articleDao.deleteArticle(articleEntity);
	}
	
	
	
	
	

}
