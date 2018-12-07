package com.upday.editor.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.dao.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Repository
@Slf4j
public class ArticleDaoImpl implements ArticleDao {
	
	
	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public ArticleEntity createArticle(ArticleEntity article) {
			log.debug("Creating new article with header", article.getHeader());
			return articleRepository.save(article);
		
		
	}

	@Override
	public ArticleEntity updateArticle(ArticleEntity article) {
		log.debug("Updating article with header", article.getHeader());
		return articleRepository.save(article);
	}

	@Override
	public List<ArticleEntity> getArticles() {
		log.debug("Fetching all the articles");
		return (List<ArticleEntity>)articleRepository.findAll();
	}

	@Override
	public ArticleEntity getArticleByUUID(String articleUUID) {
		log.debug("Get article by articleUUID", articleUUID);
		return articleRepository.findOne(articleUUID);
	}

	@Override
	public void deleteArticle(ArticleEntity article) {
		log.debug("Deleting the article with articleUUID", article.getArticleUUID());
		articleRepository.delete(article);
		
	}

}
