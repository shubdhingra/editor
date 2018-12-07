package com.upday.editor.domain;

import java.time.LocalDateTime;

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
public class ArticleResource {
	
	private String articleUUID;

}
