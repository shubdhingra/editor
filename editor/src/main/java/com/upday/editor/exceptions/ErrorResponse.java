package com.upday.editor.exceptions;

public class ErrorResponse {
	private int errorCode;
	private String message;
	
	
	public ErrorResponse(String msg, int errorCode) {
		
		this.errorCode = errorCode;
		this.message = msg;
	}
	
	public ErrorResponse() {		
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
