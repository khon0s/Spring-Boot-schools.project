package com.fortune.exceptions;

public class StudentServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StudentServiceException() {
        super();
    }
	
	public StudentServiceException(String message) {
        super(message);
    }

}
