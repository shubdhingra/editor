package com.upday.editor.dao.repository;

import org.springframework.stereotype.Repository;

import com.upday.editor.dao.entity.ArticleEntity;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {
	

}
