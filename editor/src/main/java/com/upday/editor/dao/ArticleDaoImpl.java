package com.upday.editor.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import com.upday.editor.constants.EditorConstants;
import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.exceptions.EditorDaoException;
import com.upday.editor.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Repository
@Slf4j
public class ArticleDaoImpl implements ArticleDao {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public ArticleEntity createArticle(ArticleEntity article) {
		try {
			log.debug("Creating new article with header", article.getHeader());
			return articleRepository.save(article);
		}catch(ObjectOptimisticLockingFailureException ex) {
			log.error(EditorConstants.ARTICLE_CREATION_FAIL_MSG, ex);
            throw new EditorDaoException(EditorConstants.ARTICLE_CREATION_FAIL_MSG, ex);
		}catch (RuntimeException exception) {
            log.error(EditorConstants.ARTICLE_CREATION_FAIL_MSG, exception);
            throw new EditorDaoException(EditorConstants.ARTICLE_CREATION_FAIL_MSG, exception);
        }
		

	}

	@Override
	public ArticleEntity updateArticle(ArticleEntity article) {
		try {
			log.debug("Updating article with header", article.getHeader());
			return articleRepository.save(article);
		}catch(ObjectOptimisticLockingFailureException ex) {
			log.error(EditorConstants.ARTICLE_UPDATION_FAIL_MSG, ex);
            throw new EditorDaoException(EditorConstants.ARTICLE_UPDATION_FAIL_MSG, ex);
		}catch (RuntimeException exception) {
            log.error(EditorConstants.ARTICLE_UPDATION_FAIL_MSG, exception);
            throw new EditorDaoException(EditorConstants.ARTICLE_UPDATION_FAIL_MSG, exception);
        }
		
	}

	@Override
	public List<ArticleEntity> getArticles() {
		log.debug("Fetching all the articles");
		return (List<ArticleEntity>) articleRepository.findAll();
	}

	@Override
	public ArticleEntity getArticleByUUID(String articleUUID) {
		log.debug("Get article by articleUUID", articleUUID);
		return articleRepository.findOne(articleUUID);
	}

	@Override
	public void deleteArticle(ArticleEntity article) {
		try {
			log.debug("Deleting the article with articleUUID", article.getArticleUUID());
			articleRepository.delete(article);

		}catch (RuntimeException exception) {
            log.error(EditorConstants.ARTICLE_DELETION_FAIL_MSG, exception);
            throw new EditorDaoException(EditorConstants.ARTICLE_DELETION_FAIL_MSG, exception);
        }
		
	}

	@Override
	public List<ArticleEntity> getArticlesByAuthor(String author) {
		log.debug("Fetching all the articles by author {}",author );
		return articleRepository.findArticlesByAuthor(author);
	}

	@Override
	public List<ArticleEntity> getArticlesByKeywords(String keywords) {
		log.debug("Fetching all the articles by keywords {}", keywords );
		return articleRepository.findArticlesbyKeywords(keywords);
	}


}
