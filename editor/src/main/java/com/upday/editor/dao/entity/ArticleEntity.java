package com.upday.editor.dao.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.upday.editor.dto.AuthorDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Article", uniqueConstraints = {@UniqueConstraint(columnNames = {"HEADER", "AUTHOR"})})
public class ArticleEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="articleUUID")
	private String articleUUID;
	
	@Column(name="HEADER")
	private String header;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="TEXT", columnDefinition = "TEXT")
	private String text;
	
	@Column(name="KEYWORDS")
	private String keywords;
	
	@Column(name = "PUBLISH_DATE")
	private LocalDateTime publishDate;
	
	@Column(name="AUTHOR")
	private String author;
	
	@Version
	@Column(name = "Etag")
	private int eTag;
	

}
