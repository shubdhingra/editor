package com.upday.editor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.upday.editor.constants.EditorConstants;
import com.upday.editor.dao.ArticleDao;
import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.domain.ArticleResource;
import com.upday.editor.dto.ArticleDto;
import com.upday.editor.exceptions.EditorDaoException;
import com.upday.editor.exceptions.EditorServiceException;
import com.upday.editor.exceptions.ResourceNotFoundException;
import com.upday.editor.model.Article;
import com.upday.editor.model.SearchCriteria;
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
	public ArticleResource createArticle(ArticleDto article) {
		
		try {
			ArticleEntity articleEntity = modelMapper.map(article, ArticleEntity.class);
			articleEntity.setArticleUUID(EditorUtil.getUUID());
			log.debug("Creating new aapplication with article UUID :{}", articleEntity.getArticleUUID());
			ArticleEntity persistedArticleEntity = articleDao.createArticle(articleEntity);
			return modelMapper.map(persistedArticleEntity, ArticleResource.class);
			
		}catch (EditorDaoException e) {
			log.error(EditorConstants.EDITORDAOEXCEPTION, e);
            throw new EditorServiceException(EditorConstants.INTERNAL_PROCESSING_ERROR);
        }
	
	}

	@Override
	public List<ArticleResource> getArticles(String search) {
		List<ArticleEntity> articleEntity = null;
		List<ArticleResource> articlesresources = new ArrayList<>();
		if (search != null) {
			SearchCriteria sc = EditorUtil.getSearchCriteria(search);
			if (sc.getKey().equalsIgnoreCase(EditorConstants.AUHTOR)) {
				articleEntity = articleDao.getArticlesByAuthor(sc.getValue());
			} else if (sc.getKey().equalsIgnoreCase(EditorConstants.KEYWORDS)) {
				articleEntity = articleDao.getArticlesByKeywords(sc.getValue());
			} else {
				log.error(EditorConstants.INVALID_SEARCH_CRITERIA, search);
				throw new ResourceNotFoundException(EditorConstants.INVALID_SEARCH_CRITERIA);
			}

		} else {

			articleEntity = articleDao.getArticles();
			articleEntity.forEach(article -> {
				ArticleResource art = modelMapper.map(article, ArticleResource.class);
				articlesresources.add(art);
			});
			
		}
		return articlesresources;
	}

	@Override
	public ArticleResource getArticleById(String articleUUID) {
		ArticleEntity articleEntity = articleDao.getArticleByUUID(articleUUID);
		return modelMapper.map(articleEntity, ArticleResource.class);
	}

	@Override
	public ArticleResource updateArticle(ArticleDto article, String articleUUID, String ifMatch) {
		
		try {
			ArticleEntity articleEntity = articleDao.getArticleByUUID(articleUUID);
			ArticleEntity newArticleEntity = modelMapper.map(article, ArticleEntity.class);
			newArticleEntity.setArticleUUID(articleUUID);
			newArticleEntity.setETag(Integer.valueOf(ifMatch));
			return modelMapper.map(articleDao.updateArticle(newArticleEntity), ArticleResource.class);
			
		}catch (EditorDaoException e) {
			log.error(EditorConstants.EDITORDAOEXCEPTION, e);
            throw new EditorServiceException(e.getMessage());
        }
		
	}

	@Override
	public void deleteArticle(String articleUUID) {
		try {
			// 1. check whether article exists or not
			ArticleEntity articleEntity = articleDao.getArticleByUUID(articleUUID);
			log.debug(EditorConstants.DELETING_ARTICLE_WITH_HEADER, articleEntity.getHeader());
			articleDao.deleteArticle(articleEntity);
		}catch (EditorDaoException e) {
            log.error(EditorConstants.EDITORDAOEXCEPTION, e);
            throw new EditorServiceException(e.getMessage());
        }
		
	}

}
