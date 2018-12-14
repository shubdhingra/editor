package com.upday.editor.service;

import static org.mockito.Mockito.when;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.upday.editor.dao.ArticleDao;
import com.upday.editor.dao.ArticleDaoImpl;
import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.dto.ArticleDto;
import com.upday.editor.exceptions.EditorDaoException;
import com.upday.editor.exceptions.EditorServiceException;

public class EditorServiceTest {

	@Mock
	private ArticleDao articleDao = Mockito.mock(ArticleDaoImpl.class);

	private EditorServiceBaseTest daoData = new EditorServiceBaseTest();

	@Test(dataProvider = "ArticleModel")
	public void createArticleTest(ArticleDto article) throws SerialException, SQLException {
		when(articleDao.getArticleByHeaderAndAuthor(Mockito.any(), Mockito.any())).thenReturn(null);
		when(articleDao.createArticle(Mockito.any())).thenReturn((ArticleEntity) daoData.ArticleEntity()[0][0]);

		Assert.assertNotNull(article);
	}

	@Test(dataProvider = "ArticleModel", expectedExceptions = EditorServiceException.class)
	public void createArticleTestWithEditorDaoException(ArticleDto article) throws SerialException, SQLException {
		when(articleDao.createArticle(Mockito.any())).thenThrow(new EditorDaoException(""));
		Assert.assertNotNull(article);
	}

	@Test(dataProvider = "ArticleModel", expectedExceptions = EditorServiceException.class)
	public void createArticleWithDuplicateEntryTest(ArticleDto article) throws SerialException, SQLException {
		when(articleDao.getArticleByHeaderAndAuthor(Mockito.any(), Mockito.any()))
				.thenReturn((ArticleEntity) daoData.ArticleEntity()[0][0]);
		when(articleDao.createArticle(Mockito.any())).thenThrow(new EditorDaoException(""));
		Assert.assertNotNull(article);
	}

}
