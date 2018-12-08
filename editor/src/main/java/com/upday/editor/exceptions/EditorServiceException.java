package com.upday.editor.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class EditorServiceException extends RuntimeException {
	
	 private int statusCode;

	public EditorServiceException(int errorCode, String message) {
        super(message);
        this.statusCode = errorCode;
    }
	
	public EditorServiceException(String message) {
        super(message);

    }
}
