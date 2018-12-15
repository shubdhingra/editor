package com.upday.editor.exceptions;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class Editor Exception.
 *
 * @author Shubham Dhingra
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EditorException extends RuntimeException {

    /** The exception message. */
    private String exceptionMessage;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

   /**
    * To instantiate EditorException
    * @param message
    */
    public EditorException(String message) {
        super(message.toString());
        this.exceptionMessage = message;
    }

    public EditorException(ErrorResponse error, HttpStatus status) {
        super(error.getMessage());
        this.exceptionMessage = error.getMessage();
    }

    public EditorException(Exception exception) {
        super(exception);
    }

    public EditorException(String message, Exception exception) {
        super(message, exception);
    }
    
    public String getExceptionMessage() {
        return exceptionMessage;
    }

    /**
     * 
     * @param exceptionMessage
     */
    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

}

