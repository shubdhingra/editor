package com.upday.editor.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

import java.lang.reflect.Method;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upday.editor.dto.ArticleDto;
import org.testng.annotations.DataProvider;

import lombok.Getter;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseTestContext extends AbstractTestNGSpringContextTests {

	protected static final ManualRestDocumentation REST_DOCUMENTATION = new ManualRestDocumentation(
			"build/apidoc-snippets");

	@Getter
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Getter
	private ObjectMapper objectMapper;
	
	@BeforeMethod
    public void setup(final Method method) throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = webAppContextSetup(webApplicationContext).apply(documentationConfiguration(REST_DOCUMENTATION)).build();
        REST_DOCUMENTATION.beforeTest(getClass(), method.getName());
    }
	
	 @AfterMethod
	    public void tearDown() {
	        REST_DOCUMENTATION.afterTest();
	    }
	 
	 
	 @DataProvider(name = "createArticleDto")
	 protected static Object[][] createArticleDto() {
		 return new Object[][] {{ArticleDto.builder().author("dummy").description("dummy data article").header("Dummy Article")
			 .keywords("dummy data").build()}};
	 }
	 
	 @DataProvider(name = "updateArticleDto")
	 protected static Object[][] updateArticleDto() {
		 return new Object[][] {{ArticleDto.builder().author("dummyUpdated").description("dummy data article").header("Dummy Article")
			 .keywords("dummy updated data").build()}};
	 }

}
