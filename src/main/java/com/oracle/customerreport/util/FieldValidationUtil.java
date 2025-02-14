package com.oracle.customerreport.util;

import com.oracle.customerreport.exception.InvalidFieldValueException;

/**
 * Utility class for field validation.
 * 
 * This class provides utility methods for validating input strings, ensuring
 * that the strings are not null or empty.
 * 
 */
public class FieldValidationUtil {

	/**
	 * Validates the input string for null or empty values.
	 * 
	 * This method checks whether the input string is null or empty, and throws an
	 * InvalidFieldValueException, if it is invalid.
	 * 
	 * @param string      The input string to validate
	 * @param stringField The field name for better error messaging
	 * @return The validated string
	 * @throws InvalidFieldValueException If the string is null or empty
	 */
	public static String validate(String string, String stringField) throws InvalidFieldValueException {
		if (null == string || string.isEmpty())
			throw new InvalidFieldValueException("Error in line: " + string + " for the field " + stringField);
		return string;
	}
}
