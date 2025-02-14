package com.oracle.customerreport.report;

import java.util.List;

import com.oracle.customerreport.entity.Customer;

/**
 * Interface for generating reports based on customer data.
 */
public interface Report {

	/**
	 * Generates a report for the given list of customers.
	 * 
	 * @param customers A list of Customer objects to generate the report from
	 */
	void generateReport(List<Customer> customers);

}
