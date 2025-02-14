package com.oracle.customerreport.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.oracle.customerreport.exception.InvalidFieldValueException;

public class FieldValidationUtilTest {

	/**
	 * Test for validate method from FieldValidationUtil. This verifies that
	 * validation works and throws exception for empty or null values.
	 */
	@Test
	void testValidateInvalidField() {
		// Act & Assert
		assertThrows(InvalidFieldValueException.class, () -> {
			FieldValidationUtil.validate("", "geoZone");
		});

		assertThrows(InvalidFieldValueException.class, () -> {
			FieldValidationUtil.validate(null, "teamCode");
		});
	}
}
