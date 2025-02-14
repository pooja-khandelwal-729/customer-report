package com.oracle.customerreport.exception;

/**
 * Custom exception class to handle invalid field value exceptions.
 * 
 * This exception is thrown when there is an issue with the value of a field,
 * such as an invalid or improperly formatted input.
 * 
 */
public class InvalidFieldValueException extends Exception {

	/**
	 * Constructor for the {@link InvalidFieldValueException} class.
	 * <p>
	 * This constructor initializes the exception with a custom error message.
	 * </p>
	 * 
	 * @param message The error message to be associated with this exception
	 */
	public InvalidFieldValueException(String message) {
		super(message);
	}

}
