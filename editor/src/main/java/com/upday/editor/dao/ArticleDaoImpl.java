package com.upday.editor.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import com.upday.editor.constants.EditorConstants;
import com.upday.editor.constants.MessageConstants;
import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.exceptions.EditorDaoException;
import com.upday.editor.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Article DAO IMPL
 * 
 * @author Shubham Dhingra
 *
 */
@Transactional
@Repository
@Slf4j
public class ArticleDaoImpl implements ArticleDao {

	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * Create the article in editor db
	 * 
	 * @param {@link ArticleEntity}
	 * @return {@link ArticleEntity}
	 */
	@Override
	public ArticleEntity createArticle(ArticleEntity article) {
		try {
			log.debug("Creating new article with header", article.getHeader());
			return articleRepository.save(article);
		} catch (ObjectOptimisticLockingFailureException ex) {
			log.error(EditorConstants.ARTICLE_CREATION_FAIL_MSG, ex);
			throw new EditorDaoException(EditorConstants.ARTICLE_CREATION_FAIL_MSG, ex);
		} catch (RuntimeException exception) {
			log.error(EditorConstants.ARTICLE_CREATION_FAIL_MSG, exception);
			throw new EditorDaoException(EditorConstants.ARTICLE_CREATION_FAIL_MSG, exception);
		}

	}

	/**
	 * Updates the article in editor db
	 * 
	 * @param {@link ArticleEntity}
	 * @return {@link ArticleEntity}
	 */
	@Override
	public ArticleEntity updateArticle(ArticleEntity article) {
		try {
			log.debug("Updating article with header", article.getHeader());
			return articleRepository.save(article);
		} catch (ObjectOptimisticLockingFailureException ex) {
			log.error(EditorConstants.ARTICLE_UPDATION_FAIL_MSG, ex.getMostSpecificCause().getMessage());
			throw new EditorDaoException(MessageConstants.ALREADY_MODIFIED, ex);
		} catch (RuntimeException exception) {
			log.error(EditorConstants.ARTICLE_UPDATION_FAIL_MSG, exception);
			throw new EditorDaoException(EditorConstants.ARTICLE_UPDATION_FAIL_MSG, exception);
		}

	}

	/**
	 * Fetches the list of articles from editor db
	 * 
	 * @param {@link ArticleEntity}
	 * @return List of {@link ArticleEntity}
	 */
	@Override
	public List<ArticleEntity> getArticles() {
		log.debug("Fetching all the articles");
		return (List<ArticleEntity>) articleRepository.findAll();
	}

	/**
	 * Fetches the article from editor db
	 * 
	 * @param String article id
	 * @return {@link ArticleEntity}
	 */
	@Override
	public ArticleEntity getArticleByUUID(String articleUUID) {
		log.debug("Get article by articleUUID", articleUUID);
		return articleRepository.findOne(articleUUID);
	}

	/**
	 * Fetches the article from editor db based on header and author
	 * 
	 * @param String article header, author
	 * @return {@link ArticleEntity}
	 */
	@Override
	public ArticleEntity getArticleByHeaderAndAuthor(String header, String author) {
		log.debug("Get article with Header {} and written by Author {}", header, author);
		return articleRepository.getByHeaderAndAuthor(header, author);
	}

	/**
	 * deletes the article from editor db
	 * 
	 * @param {@link ArticleEntity}
	 * @return
	 */
	@Override
	public void deleteArticle(ArticleEntity article) {
		try {
			log.debug("Deleting the article with articleUUID", article.getArticleUUID());
			articleRepository.delete(article);

		} catch (RuntimeException exception) {
			log.error(EditorConstants.ARTICLE_DELETION_FAIL_MSG, exception);
			throw new EditorDaoException(EditorConstants.ARTICLE_DELETION_FAIL_MSG, exception);
		}

	}

	/**
	 * Fetches the list of articles from editor db on basis of some specification
	 * 
	 * @param {@link ArticleEntity}
	 * @return List of {@link ArticleEntity}
	 */
	@Override
	public List<ArticleEntity> getArticlesbySpec(Specification<ArticleEntity> spec) {
		log.debug("Fetching articles on the basis of provided specification {}", spec.toString());
		return articleRepository.findAll(spec);
	}

}
