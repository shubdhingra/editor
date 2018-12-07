package com.upday.editor.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.upday.editor.dto.AuthorDto;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.validation.annotation.Validated;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/api/v1/authors", produces = {HAL_JSON_VALUE, APPLICATION_JSON_VALUE})
public class AuthorController {
	
	@ResponseStatus(CREATED)
    @PostMapping
    @ApiOperation("createAuthor")
	public void createAuthor(@RequestBody @Validated(AuthorDto.CreateAuthor.class) AuthorDto createAuthorDto ){
		
		log.debug("Creating author {}", createAuthorDto.toString());
		
		
	}
	
	@ResponseStatus(OK)
    @GetMapping
    @ApiOperation("getAllAuthors")
	public void getAllAuthors(@RequestBody @Validated(AuthorDto.CreateAuthor.class) AuthorDto createAuthorDto ){
		
		log.debug("Creating author {}", createAuthorDto.toString());
		
		
	}
	
	@ResponseStatus(OK)
	@RequestMapping(value = "/{authUUID}", method = RequestMethod.GET)
    @ApiOperation("getAuthorByAuthUUID")
	public void getAuthor(@RequestBody @Validated(AuthorDto.CreateAuthor.class) AuthorDto createAuthorDto ){
		
		log.debug("Creating author {}", createAuthorDto.toString());
		
		
	}
	
	@ResponseStatus(OK)
	@RequestMapping(value = "/{authUUID}", method = RequestMethod.PUT)
    @ApiOperation("updateAuthorByAuthUUID")
	public void updateAuthorByAuthUUID(@RequestBody @Validated(AuthorDto.CreateAuthor.class) AuthorDto createAuthorDto ){
		
		log.debug("Creating author {}", createAuthorDto.toString());
		
		
	}
	
	@ResponseStatus(NO_CONTENT)
	@RequestMapping(value = "/{authUUID}", method = RequestMethod.DELETE)
    @ApiOperation("deletAuthorByAuthUUID")
	public void deleteAuthorByAuthUUID(@RequestBody @Validated(AuthorDto.CreateAuthor.class) AuthorDto createAuthorDto ){
		
		log.debug("Creating author {}", createAuthorDto.toString());
		
		
	}

}
