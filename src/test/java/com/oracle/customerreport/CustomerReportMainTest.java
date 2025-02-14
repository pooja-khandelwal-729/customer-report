package com.oracle.customerreport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.oracle.customerreport.entity.Customer;
import com.oracle.customerreport.exception.InvalidFieldValueException;

class CustomerReportMainTest {

	/**
	 * Test for validating the getCustomerList method with correct input. This test
	 * simulates user input and verifies that the customer list is correctly
	 * created.
	 */
	@Test
	void testGetCustomerListValidInput() throws IOException, InvalidFieldValueException {
		// Arrange
		String input = "1001,2001,North,teamA,projectX,2122s\n1002,2002,South,teamB,projectY,3443s\n\r";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in); // Redirect standard input

		CustomerReportMain reportMain = new CustomerReportMain();

		// Act
		List<Customer> customers = reportMain.getCustomerList();

		// Assert
		assertNotNull(customers);
		assertEquals(2, customers.size());
		assertEquals(1001, customers.get(0).getCustomerId());
		assertEquals("North", customers.get(0).getGeoZone());
		assertEquals(2122, customers.get(0).getBuildDuration().getSeconds());
	}

	/**
	 * Test for the getCustomer method when input data is invalid (e.g., incorrect
	 * duration format). The method should throw InvalidFieldValueException.
	 */
	@Test
	void testGetCustomerInvalidInput() {
		// Arrange
		String invalidInput = "1001,2001,North,teamA,projectX,INVALID_DURATION";

		// Act
		CustomerReportMain reportMain = new CustomerReportMain();

		// Assert
		assertThrows(InvalidFieldValueException.class, () -> {
			reportMain.getCustomer(invalidInput);
		});

	}

	/**
	 * Test for main method - Simulating the main method's behavior with valid
	 * input.
	 */
	@Test
	void testMainValidInput() throws IOException {
		// Arrange
		String input = "1001,2001,North,teamA,projectX,10s\n\r";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		// Capture the output of the main method
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		// Act
		CustomerReportMain.main(new String[] {});

		// Assert
		assertTrue(outputStream.toString().contains("The number of unique customerId for each contractId"));
		assertTrue(outputStream.toString().contains("The number of unique customerId for each geoZone"));
		assertTrue(outputStream.toString().contains("The average buildduration for each geoZone"));
		assertTrue(outputStream.toString().contains("The list of unique customerId for each geoZone"));
	}
}
