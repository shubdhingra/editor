package com.upday.editor.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.upday.editor.constants.MessageConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto   {
	
	@Size(message = MessageConstants.HEADER_CONSTRAINT_MESSAGE, max = 20, groups = CreateArticle.class)
	@NotEmpty(message = MessageConstants.HEADER_EMPTY_MESSAGE, groups = CreateArticle.class)
	private String header;
	@Size(max = 255)
	private String description;
	private String text;
	private String keywords;
	private AuthorDto author;
	private LocalDateTime publishDate;
	
	
	public interface CreateArticle{
		
	}

}
