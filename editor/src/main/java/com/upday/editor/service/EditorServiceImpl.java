package com.upday.editor.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.upday.editor.constants.EditorConstants;
import com.upday.editor.constants.ErrorConstants;
import com.upday.editor.constants.MessageConstants;
import com.upday.editor.dao.ArticleDao;
import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.domain.ArticleResource;
import com.upday.editor.dto.ArticleDto;
import com.upday.editor.exceptions.EditorDaoException;
import com.upday.editor.exceptions.EditorServiceException;
import com.upday.editor.exceptions.ResourceNotFoundException;
import com.upday.editor.model.SearchCriteria;
import com.upday.editor.util.ArticleSpecification;
import com.upday.editor.util.EditorUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EditorServiceImpl implements EditorService {

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ArticleResource createArticle(ArticleDto article) {

		try {
			ArticleEntity oldArticleEntity = articleDao.getArticleByHeaderAndAuthor(article.getHeader(), article.getAuthor());
			if(oldArticleEntity!=null) {
				throw new EditorServiceException(HttpStatus.CONFLICT.value(), MessageConstants.DUPLICATE_ENTRY);
			}
			ArticleEntity articleEntity = modelMapper.map(article, ArticleEntity.class);
			articleEntity.setArticleUUID(EditorUtil.getUUID());
			log.debug("Creating new aapplication with article UUID :{}", articleEntity.getArticleUUID());
			ArticleEntity persistedArticleEntity = articleDao.createArticle(articleEntity);
			return modelMapper.map(persistedArticleEntity, ArticleResource.class);

		} catch (EditorDaoException e) {
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

		} catch (EditorDaoException e) {
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
		} catch (EditorDaoException e) {
			log.error(EditorConstants.EDITORDAOEXCEPTION, e);
			throw new EditorServiceException(e.getMessage());
		}

	}

	@Override
	public Page<ArticleResource> getArticlesbySpec(String author, String keywords, String fromDate, String toDate,
			Pageable pageable) {
		ArticleEntity filter = new ArticleEntity();
		filter.setAuthor(author);
		filter.setKeywords(keywords);
		Specification<ArticleEntity> spec = new ArticleSpecification(filter);
		List<ArticleEntity> articles = articleDao.getArticlesbySpec(spec);
		List<ArticleResource> articleResources = new ArrayList<>();
		if (articles == null) {
			throw new EditorServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ErrorConstants.UNABLE_TO_FETCH_ARTICLES);
		}
		
		articles.forEach(article -> {
		articleResources.add(modelMapper.map(article, ArticleResource.class));
		});

		return new PageImpl<>(articleResources, pageable, articleResources.size());
	}

}
