package com.upday.editor.exceptions;

public class EditorDaoException extends RuntimeException {
	
	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    private int statusCode;

    /**
     * Instantiates a new editor dao exception.
     *
     * @param message
     *            the message
     */
    public EditorDaoException(String message) {
        super(message);
    }
    
    public EditorDaoException() {
        super();
    }

    /**
     * Instantiates a new editor dao exception.
     *
     * @param message
     *            the message
     * @param exception
     *            the exception
     */
    public EditorDaoException(String message, Exception exception) {
        super(message, exception);
    }
    
    /**
     * 
     * @param errorCode
     * @param message
     */
    public EditorDaoException(int errorCode, String message) {
        super(message);
        this.statusCode = errorCode;
    }

}
