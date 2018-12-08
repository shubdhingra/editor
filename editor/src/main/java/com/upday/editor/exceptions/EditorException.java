package com.upday.editor.exceptions;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class AppManagerException.
 *
 * @author avdm1h
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EditorException extends RuntimeException {

    /** The exception message. */
    private String exceptionMessage;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

   
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
        return   exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

}

