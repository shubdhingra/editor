package com.upday.editor.exceptions;

import lombok.Getter;
import lombok.Setter;
/**
 * Editor Service exception
 * @author Shubham Dhingra
 *
 */
@Getter 
@Setter
public class EditorServiceException extends RuntimeException {
	/**
	 * Https Status code
	 */
	 private int statusCode;

	 /**
	  * to instantiate EditorServiceException
	  * @param errorCode
	  * @param message
	  */
	public EditorServiceException(int errorCode, String message) {
        super(message);
        this.statusCode = errorCode;
    }
	
	public EditorServiceException(String message) {
        super(message);

    }
}
