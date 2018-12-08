package com.upday.editor.model;

import java.time.LocalDateTime;
import java.util.List;

import com.upday.editor.dto.AuthorDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	
	private String articleUUID;
	private String header;
	private String description;
	private String text;
	private String keywords;
	private String author;
	private LocalDateTime publishDate;
	private int eTag;

}
