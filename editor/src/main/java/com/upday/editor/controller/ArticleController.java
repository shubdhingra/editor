package com.upday.editor.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.HttpHeaders.IF_MATCH;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.upday.editor.constants.MessageConstants;
import com.upday.editor.domain.ArticleResource;
import com.upday.editor.dto.ArticleDto;
import com.upday.editor.exceptions.EditorException;
import com.upday.editor.exceptions.ErrorResponse;
import com.upday.editor.service.ArticleServiceImpl;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/articles", produces = { APPLICATION_JSON_VALUE })
public class ArticleController {

	@Autowired
	private ArticleServiceImpl articleService;

	@ResponseStatus(CREATED)
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation("createArticle")
	public ResponseEntity<ArticleResource> createArticle(@RequestBody @Valid ArticleDto createArticleDto) {

		log.debug("Creating Article {}", createArticleDto.toString());
		ArticleResource articleResource = articleService.createArticle(createArticleDto);

		return new ResponseEntity<>(articleResource, HttpStatus.CREATED);

	}

	@ResponseStatus(OK)
	@RequestMapping(value = "/{articleUUID}", method = RequestMethod.PUT)
	@ApiOperation("updateArticleByAuthUUID")
	public ResponseEntity<ArticleResource> updateArticleByAuthUUID(
			@RequestBody @Validated(ArticleDto.CreateArticle.class) ArticleDto createArticleDto,
			@PathVariable String articleUUID, @NotNull @RequestHeader(IF_MATCH) final String ifMatch) {

		log.debug("Updating Article {}", createArticleDto.getHeader());
		ArticleResource articleResource = articleService.updateArticle(createArticleDto, articleUUID, ifMatch);
		return new ResponseEntity<>(articleResource, HttpStatus.OK);

	}

	@ResponseStatus(NO_CONTENT)
	@RequestMapping(value = "/{articleUUID}", method = RequestMethod.DELETE)
	@ApiOperation("deletArticleByAuthUUID")
	public void deleteArticleByAuthUUID(@PathVariable String articleUUID) {

		log.debug("Deleting Article with ID {}", articleUUID);
		articleService.deleteArticle(articleUUID);

	}

	/*@ResponseStatus(OK)
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation("getAllArticles")
	public ResponseEntity<List<ArticleResource>> getAllArticles(
			@RequestParam(value = "search", required = false) String search) {

		log.debug("Get details of all Article");
		List<ArticleResource> articleResource = articleService.getArticles(search);
		return ResponseEntity.ok(articleResource);

	}
*/
	@ResponseStatus(OK)
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation("getAllArticles")
	public Page<ArticleResource> getArticlesBySpec(
			@RequestParam(value = "author", required = false) final String author,
			@RequestParam(value = "keywords", required = false) final String keywords,
			@RequestParam(value = "fromDate", required = false) final String fromDate,
			@RequestParam(value = "toDate", required = false) final String toDate, final Pageable pageable) {

		log.debug("Get details of all Article");
		return articleService.getArticlesbySpec(author, keywords, fromDate, toDate, pageable);

	}

	@ResponseStatus(OK)
	@RequestMapping(value = "/{articleUUID}", method = RequestMethod.GET)
	@ApiOperation("getArticleByAuthUUID")
	public ResponseEntity<ArticleResource> getArticleByUUID(
			@PathVariable @NotNull(message = MessageConstants.ARTICLE_UUID_CONSTRAINT) String articleUUID) {

		log.debug("Get details of Article with id {}", articleUUID);
		ArticleResource articleResource = articleService.getArticleById(articleUUID);
		return ResponseEntity.ok(articleResource);

	}

}
