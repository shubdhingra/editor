package com.upday.editor.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.upday.editor.constants.EditorConstants;
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
	
	@Size(message = MessageConstants.HEADER_CONSTRAINT_MESSAGE, max = EditorConstants.MAX_HEADER_SIZE_ALLOWED)
	@NotEmpty(message = MessageConstants.HEADER_EMPTY_MESSAGE)
	private String header;
	@Size(max = EditorConstants.MAX_DESCRIPTION_SIZE_ALLOWED)
	private String description;
	private String text;
	private String keywords;
	private String author;
	private LocalDateTime publishDate;
	
	
	public interface CreateArticle{
		
	}

}
