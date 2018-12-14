package com.upday.editor.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertNotNull;

import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.gson.Gson;

import com.upday.editor.domain.ArticleResource;
import com.upday.editor.dto.ArticleDto;

public class EditorControllerTest extends BaseTestContext{
	
	ArticleDto article;
	private String articleId;
	
	@BeforeMethod(alwaysRun = true)
	public void initMocks() {
		
		article = ArticleDto.builder().author("testAuthor").description("testDescription")
				.header("testHeader").keywords("testKeywords").text("testText").build();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(dataProvider = "createArticleDto", priority=1)
	public void shouldReturnArticleAfterCreation(ArticleDto articleDto) throws Exception {

		final MockHttpServletRequestBuilder requestBuilder =
				post("/api/v1/articles").contentType(MediaType.APPLICATION_JSON).
				content(getObjectMapper().writeValueAsString(articleDto));
		
		 MvcResult result = getMockMvc().perform(requestBuilder).andExpect(status().isCreated()).andDo(
	                document("shouldReturnArticleAfterCreation", 
	                		preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
	                .andReturn();
		  ArticleResource a = new Gson().fromJson(result.getResponse().getContentAsString(), ArticleResource.class);
		  articleId = a.getArticleUUID();
}
	
	@Test(priority = 2)
	public void shouldReturnDetailsByArticleUUID() throws Exception {
		final MockHttpServletRequestBuilder requestBuilder =
				get("/api/v1/articles/{articleUUID}", articleId).contentType(MediaType.APPLICATION_JSON);
		
		 MvcResult result = getMockMvc().perform(requestBuilder).andExpect(status().isOk())
	                .andDo(document("shouldReturnDetailsByArticleUUID", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))).andReturn();
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void shouldReturnAllArticles() throws Exception {
		final MockHttpServletRequestBuilder requestBuilder =
				get("/api/v1/articles").contentType(MediaType.APPLICATION_JSON);
		
		 assertNotNull(getMockMvc().perform(requestBuilder).andExpect(status().isOk())
	                .andDo(document("shouldReturnAllArticles", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))).andReturn());
	}
	
	
	@Test(priority = 4)
	public void shouldDeleteArticleByArticleUUID() throws Exception {
		final MockHttpServletRequestBuilder requestBuilder =
				delete("/api/v1/articles/{articleUUID}", articleId).contentType(MediaType.APPLICATION_JSON);
		
		getMockMvc().perform(requestBuilder).andExpect(status().isNoContent())
	                .andDo(document("shouldReturnAllArticles", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))).andReturn();
	}
}
