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
import com.upday.editor.constants.ErrorConstants;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RestController
@Slf4j
public class EditorExceptionHandler {

	@ExceptionHandler(EditorException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(), HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	@ExceptionHandler(value = EditorServiceException.class)
	public ResponseEntity<ErrorResponse> handlerForAppManagerServiceException(EditorServiceException ex) {

		if (ex.getStatusCode() == 0) {
			ex.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}
		// return createVndErrorsResponseEntity(new
		// AppManagerException(createList(exception.getMessage())),
		// HttpStatus.valueOf(exception.getStatusCode()));
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(), ex.getStatusCode()),
				HttpStatus.valueOf(ex.getStatusCode()));
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handlerForHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ErrorConstants.INVALID_PAYLOAD, HttpStatus.BAD_REQUEST.value()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerForResourceNotFoundException(ResourceNotFoundException ex) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ErrorResponse> handlerForRuntimeException(Exception ex) {
		Throwable rootCause = getMostSpecificCause(ex);
		log.error("Encounterd Exception {} with Most Specific Cause {}", rootCause.getClass().getName(),
				rootCause.getMessage());
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(EditorConstants._500_INTERNAL_SERVER_ERROR_MESSAGE,
				HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

/*	@ExceptionHandler(value = ConversionFailedException.class)
	public ResponseEntity<ErrorResponse> handleConversionFailedException(ConversionFailedException ex) {
		Throwable rootCause = getMostSpecificCause(ex);
		log.error("Encounterd Exception {} with Most Specific Cause {}", rootCause.getClass().getName(),
				rootCause.getMessage());
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);

	}*/

	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
		log.error("Validation Exception {}", ex.getMessage());

		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);

	}

	/*
	 * @ExceptionHandler(Throwable.class) public ResponseEntity<?>
	 * handleException(Throwable ex) { ErrorResponse errorResponse =
	 * errorResponseComposer.compose(ex); return new
	 * ResponseEntity<ErrorResponse>(errorResponse, errorResponse.getStatus()); }
	 */

	private static Throwable getMostSpecificCause(Throwable ex) {
		Throwable t = ex;
		while (t.getCause() != null) {
			t = t.getCause();
		}
		return t;

	}

}
