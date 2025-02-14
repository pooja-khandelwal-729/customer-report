package com.oracle.customerreport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.customerreport.entity.Customer;
import com.oracle.customerreport.exception.InvalidFieldValueException;
import com.oracle.customerreport.report.AverageBuildDurationPerGeoZoneReport;
import com.oracle.customerreport.report.UniqueCustomerCountPerContractIdReport;
import com.oracle.customerreport.report.UniqueCustomerCountPerGeoZoneReport;
import com.oracle.customerreport.report.UniqueCustomerListPerGeoZoneReport;
import com.oracle.customerreport.util.FieldValidationUtil;

/**
 * This class processes input data to build a list of Customer objects,
 * validates each customer's information, and generates multiple reports based
 * on the data.
 */
public class CustomerReportMain {

	/**
	 * Main method to execute the application.
	 * 
	 * This method reads customer data from the standard input, processes the data,
	 * validates it, and then generates various reports based on the customer
	 * information.
	 * 
	 * @param args Command-line arguments (not used in this implementation)
	 */
	public static void main(String[] args) {

		List<Customer> customers;
		try {
			// Get the list of customers by reading and validating input
			customers = new CustomerReportMain().getCustomerList();
			// Generate reports based on the customer data
			generateReports(customers);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error in reading input: " + e.getMessage());
		} catch (InvalidFieldValueException e) {
			e.printStackTrace();
			System.out.println("Invalid input: " + e.getMessage());
		}
	}

	/**
	 * Reads customer data from the standard input and returns a list of customers.
	 * 
	 * This method continuously reads input lines until an empty line is entered.
	 * Each line is processed to create a Customer object. If any input is
	 * invalid, an exception will be thrown.
	 * 
	 * @return List of Customer objects
	 * @throws IOException                If there is an error reading input data
	 * @throws InvalidFieldValueException If any field value is invalid
	 */
	public List<Customer> getCustomerList() throws IOException, InvalidFieldValueException {
		System.out.println("Enter customer data:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		List<Customer> customerList = new ArrayList<>();
		String line;

		// Read input lines and create customer objects until an empty line is entered
		while (!(line = reader.readLine()).isEmpty()) {
			Customer customer = getCustomer(line);
			customerList.add(customer);
		}

		return customerList;
	}

	/**
	 * Creates a Customer instance from the provided input string.
	 * 
	 * The input string is expected to be comma-separated values containing customer
	 * details such as customer ID, contract ID, geo zone, team code, project code,
	 * and build duration. If any of the values are invalid, an
	 * InvalidFieldValueException is thrown.
	 * 
	 * @param string Input string containing customer details
	 * @return A newly created Customer object
	 * @throws InvalidFieldValueException If any field value is invalid
	 */
	public Customer getCustomer(String string) throws InvalidFieldValueException {
		String[] customerDetails = string.split(",");
		Duration buildDuration;
		String teamCode;
		int contractId;
		int customerId;
		String projectCode;
		String geoZone;

		try {
			// Parse and validate each customer detail from the input string
			customerId = Integer.parseInt(customerDetails[0]);
			contractId = Integer.parseInt(customerDetails[1]);
			geoZone = FieldValidationUtil.validate(customerDetails[2], "geoZone");
			teamCode = FieldValidationUtil.validate(customerDetails[3], "teamCode");
			projectCode = FieldValidationUtil.validate(customerDetails[4], "projectCode");
			buildDuration = Duration.parse("PT" + customerDetails[5]);

		} catch (NumberFormatException e) {
			throw new InvalidFieldValueException("Error in line " + string + ": " + e.getMessage());
		} catch (DateTimeParseException e) {
			throw new InvalidFieldValueException("Error in line " + string + ": " + e.getMessage());
		}

		// Return a new Customer instance
		Customer customer = new Customer(customerId, contractId, geoZone, teamCode, projectCode, buildDuration);
		return customer;
	}

	/**
	 * Generates reports based on the provided list of customers.
	 * 
	 * This method invokes different report classes to generate various reports.
	 * 
	 * @param customers List of Customer objects to generate the reports
	 *                  from
	 */
	private static void generateReports(List<Customer> customers) {
		// Generate the respective reports using the provided customers list
		new UniqueCustomerCountPerContractIdReport().generateReport(customers);
		new UniqueCustomerCountPerGeoZoneReport().generateReport(customers);
		new AverageBuildDurationPerGeoZoneReport().generateReport(customers);
		new UniqueCustomerListPerGeoZoneReport().generateReport(customers);
	}
}
