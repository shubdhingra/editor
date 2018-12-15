package com.upday.editor.service;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.upday.editor.dao.ArticleDao;
import com.upday.editor.dao.ArticleDaoImpl;
import com.upday.editor.dao.entity.ArticleEntity;
import com.upday.editor.domain.ArticleResource;
import com.upday.editor.dto.ArticleDto;
import com.upday.editor.exceptions.EditorDaoException;
import com.upday.editor.exceptions.EditorServiceException;
import com.upday.editor.exceptions.ResourceNotFoundException;

public class EditorServiceTest {

	private EditorServiceBaseTest daoData = new EditorServiceBaseTest();

	@Mock
	private ArticleDao articleDao = Mockito.mock(ArticleDaoImpl.class);

	@Mock
	private ModelMapper modelMapper = Mockito.mock(ModelMapper.class);;

	@InjectMocks
	private EditorServiceImpl editorService = new EditorServiceImpl();

	@BeforeMethod
	private void setup() {
		ReflectionTestUtils.setField(editorService, "articleDao", articleDao);
		ReflectionTestUtils.setField(editorService, "modelMapper", modelMapper);

	}

	@Test(dataProvider = "articleDto", dataProviderClass = EditorServiceBaseTest.class)
	public void createArticleTest(ArticleDto article) {
		when(articleDao.getArticleByHeaderAndAuthor(Mockito.any(), Mockito.any())).thenReturn(null);
		when(articleDao.createArticle(Mockito.any())).thenReturn((ArticleEntity) daoData.articleEntity()[0][0]);
		when(modelMapper.map(Mockito.any(), Mockito.eq(ArticleEntity.class)))
				.thenReturn((ArticleEntity) daoData.articleEntity()[0][0]);
		when(modelMapper.map(Mockito.any(), Mockito.eq(ArticleResource.class)))
				.thenReturn((ArticleResource) daoData.articleResource()[0][0]);
		Assert.assertNotNull(editorService.createArticle(article));
	}

	@Test(dataProvider = "articleDto", dataProviderClass = EditorServiceBaseTest.class, expectedExceptions = EditorServiceException.class)
	public void createArticleWithDaoException(ArticleDto article) {
		when(articleDao.getArticleByHeaderAndAuthor(Mockito.any(), Mockito.any()))
				.thenReturn((ArticleEntity) daoData.articleEntity()[0][0]);
		when(articleDao.createArticle(Mockito.any())).thenThrow(new EditorDaoException());
		when(modelMapper.map(Mockito.any(), Mockito.eq(ArticleEntity.class)))
				.thenReturn((ArticleEntity) daoData.articleEntity()[0][0]);
		when(modelMapper.map(Mockito.any(), Mockito.eq(ArticleResource.class)))
				.thenReturn((ArticleResource) daoData.articleResource()[0][0]);
		Assert.assertNotNull(editorService.createArticle(article));
	}

	@Test(dataProvider = "articleDto", dataProviderClass = EditorServiceBaseTest.class, expectedExceptions = EditorDaoException.class)
	public void createArticleWithDuplicateEntry(ArticleDto article) {
		when(articleDao.getArticleByHeaderAndAuthor(Mockito.any(), Mockito.any()))
				.thenReturn((ArticleEntity) daoData.articleEntity()[0][0]);
		when(articleDao.createArticle(Mockito.any())).thenReturn((ArticleEntity) daoData.articleEntity()[0][0]);
		when(modelMapper.map(Mockito.any(), Mockito.eq(ArticleEntity.class)))
				.thenReturn((ArticleEntity) daoData.articleEntity()[0][0]);
		when(modelMapper.map(Mockito.any(), Mockito.eq(ArticleResource.class)))
				.thenReturn((ArticleResource) daoData.articleResource()[0][0]);
		Assert.assertNotNull(editorService.createArticle(article));

	}

	@Test
	public void getArticlesByIdTest() {
		when(articleDao.getArticleByUUID(Mockito.any()))
				.thenReturn((ArticleEntity) daoData.articleEntity()[0][0]);
		Assert.assertNotNull(editorService.getArticleById("1234"));
	}
	
	@Test(expectedExceptions = ResourceNotFoundException.class)
	public void getArticlesByIdForNonExistentArticleTest() {
		when(articleDao.getArticleByUUID(Mockito.any()))
				.thenReturn(null);
		Assert.assertNotNull(editorService.getArticleById("1234"));
	}
	
	@Test(dataProvider = "articleDto", dataProviderClass = EditorServiceBaseTest.class) 
	public void updateArticleTest(ArticleDto articleDto) {
		when(articleDao.getArticleByUUID(Mockito.any()))
		.thenReturn((ArticleEntity) daoData.articleEntity()[0][0]);
		when(modelMapper.map(Mockito.any(), Mockito.eq(ArticleEntity.class)))
		.thenReturn((ArticleEntity) daoData.articleEntity()[0][0]);
		when(modelMapper.map(Mockito.any(), Mockito.eq(ArticleResource.class)))
		.thenReturn((ArticleResource) daoData.articleResource()[0][0]);
		Assert.assertNotNull(editorService.updateArticle(articleDto, "1234", "1"));
		
	}
	
	@Test(dataProvider = "articleDto", dataProviderClass = EditorServiceBaseTest.class, expectedExceptions = ResourceNotFoundException.class) 
	public void updateArticleFailTest(ArticleDto articleDto) {
		when(articleDao.getArticleByUUID(Mockito.any()))
		.thenReturn(null);
		when(modelMapper.map(Mockito.any(), Mockito.eq(ArticleEntity.class)))
		.thenReturn((ArticleEntity) daoData.articleEntity()[0][0]);
		when(modelMapper.map(Mockito.any(), Mockito.eq(ArticleResource.class)))
		.thenReturn((ArticleResource) daoData.articleResource()[0][0]);
		Assert.assertNotNull(editorService.updateArticle(articleDto, "1234", "1"));
		
	}
}