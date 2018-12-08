package com.upday.editor.exceptions;

public class EditorDaoException extends RuntimeException {
	
	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new app manager dao exception.
     *
     * @param message
     *            the message
     */
    public EditorDaoException(String message) {
        super(message);
    }

    /**
     * Instantiates a new app manager dao exception.
     *
     * @param message
     *            the message
     * @param exception
     *            the exception
     */
    public EditorDaoException(String message, Exception exception) {
        super(message, exception);
    }

}
