package com.upday.editor.dao.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
//@Table(name="Article", uniqueConstraints = {@UniqueConstraint(columnNames = {""})})
public class ArticleEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ArticleUUID")
	private String articleUUID;
	
	@Column(name="HEADER")
	private String header;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="TEXT", columnDefinition = "TEXT")
	private String text;
	
	@Column(name="KEYWORDS")
	private List<String> keywords;
	
	@Column(name = "PUBLISH_DATE")
	private LocalDateTime publishDate;
	
	private AuthorDto author;
	

}
