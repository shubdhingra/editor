package com.upday.editor.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
	
	private String name;
	private List<ArticleDto> articles; 

	 public interface CreateAuthor {

	    }
}
