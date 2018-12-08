package com.upday.editor.exceptions;

import javax.validation.ValidationException;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.upday.editor.constants.EditorConstants;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RestController
@Slf4j
public class EditorExceptionHandler {
	
	/*@ExceptionHandler(value = EditorException.class)
    public ResponseEntity<ErrorResponse> handlerForMindInitException(EditorException exception) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("Please contact your administrator");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }*/
	
	
	@ExceptionHandler(EditorException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(value = EditorServiceException.class)
    public ResponseEntity<ErrorResponse> handlerForAppManagerServiceException(EditorServiceException ex) {
		ErrorResponse error = new ErrorResponse();
        if (ex.getStatusCode() == 0) {
            ex.setStatusCode(HttpStatus.BAD_REQUEST.value());
        }
        //return createVndErrorsResponseEntity(new AppManagerException(createList(exception.getMessage())), HttpStatus.valueOf(exception.getStatusCode()));
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.valueOf(ex.getStatusCode()));
    }
	
	 @ExceptionHandler(value = HttpMessageNotReadableException.class)
	    public ResponseEntity<ErrorResponse> handlerForHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		 ErrorResponse error = new ErrorResponse();
			error.setMessage(ex.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(value = ResourceNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handlerForResourceNotFoundException(ResourceNotFoundException ex) {
		 ErrorResponse error = new ErrorResponse();
			error.setMessage(ex.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	    }
	 
	 @ExceptionHandler(value = RuntimeException.class)
	    public ResponseEntity<ErrorResponse> handlerForRuntimeException(Exception ex) {
	        Throwable rootCause = getMostSpecificCause(ex);
	        log.error("Encounterd Exception {} with Most Specific Cause {}", rootCause.getClass().getName(), rootCause.getMessage());
	        ErrorResponse error = new ErrorResponse();
			error.setMessage(EditorConstants._500_INTERNAL_SERVER_ERROR_MESSAGE);
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(value = ConversionFailedException.class)
	    public ResponseEntity<ErrorResponse> handleConversionFailedException(ConversionFailedException ex) {
	        Throwable rootCause = getMostSpecificCause(ex);
	        ErrorResponse error = new ErrorResponse();
	        error.setMessage(ex.getMessage());
	        log.error("Encounterd Exception {} with Most Specific Cause {}", rootCause.getClass().getName(), rootCause.getMessage());
	        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);

	    }
	 
	 @ExceptionHandler(value = ValidationException.class)
	    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
	        log.error("Validation Exception {}", ex.getMessage());
	        ErrorResponse error = new ErrorResponse();
	        error.setMessage(ex.getMessage());
	        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	       
	    }
	 
	 private static Throwable getMostSpecificCause(Throwable ex) {
	        Throwable t = ex;
	        while (t.getCause() != null) {
	            t = t.getCause();
	        }
	        return t;

	    }



}
