package com.upday.editor.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertNotNull;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.upday.editor.domain.ArticleResource;
import com.upday.editor.dto.ArticleDto;

public class EditorControllerTest extends BaseTestContext {

	private String articleId;
	private Gson gson;

	@BeforeMethod(alwaysRun = true)
	public void initMocks() {
		gson = new GsonBuilder().create();
	}

	@Test(dataProvider = "articleDto", priority = 1)
	public void shouldReturnArticleAfterCreation(ArticleDto articleDto) throws Exception {

		String json = gson.toJson(articleDto);

		final MockHttpServletRequestBuilder requestBuilder = post("/api/v1/articles")
				.contentType(MediaType.APPLICATION_JSON).content(json.toString());

		MvcResult result = getMockMvc().perform(requestBuilder).andExpect(status().isCreated())
				.andDo(document("shouldReturnArticleAfterCreation", preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())))
				.andReturn();
		ArticleResource a = new Gson().fromJson(result.getResponse().getContentAsString(), ArticleResource.class);
		articleId = a.getArticleUUID();
	}

	@Test(priority = 2)
	public void shouldReturnDetailsByArticleId() throws Exception {
		final MockHttpServletRequestBuilder requestBuilder = get("/api/v1/articles/{articleId}", articleId)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = getMockMvc().perform(requestBuilder).andExpect(status().isOk())
				.andDo(document("shouldReturnDetailsByArticleUUID", preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())))
				.andReturn();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void shouldReturnAllArticles() throws Exception {
		final MockHttpServletRequestBuilder requestBuilder = get("/api/v1/articles")
				.contentType(MediaType.APPLICATION_JSON);

		assertNotNull(getMockMvc().perform(requestBuilder).andExpect(status().isOk())
				.andDo(document("shouldReturnAllArticles", preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())))
				.andReturn());
	}

	@Test(dataProvider = "articleDto", priority = 4)
	public void shouldUpdateArticleByArticleId(ArticleDto articleDto) throws Exception {

		articleDto.setAuthor("UPDATED AUTHOR");
		String json = gson.toJson(articleDto);
		final MockHttpServletRequestBuilder requestBuilder = put("/api/v1/articles/{articleId}", articleId)
				.header(HttpHeaders.IF_MATCH, "0").contentType(MediaType.APPLICATION_JSON).content(json.toString());

		getMockMvc().perform(requestBuilder).andExpect(status().isOk()).andDo(document("shouldUpdateArticleByArticleId",
				preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))).andReturn();

	}

	@Test(priority = 5)
	public void shouldDeleteArticleByArticleId() throws Exception {
		final MockHttpServletRequestBuilder requestBuilder = delete("/api/v1/articles/{articleId}", articleId)
				.contentType(MediaType.APPLICATION_JSON);

		getMockMvc().perform(requestBuilder).andExpect(status().isNoContent()).andDo(document("shouldReturnAllArticles",
				preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))).andReturn();
	}
}
