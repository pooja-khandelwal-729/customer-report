package com.oracle.customerreport.report;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oracle.customerreport.entity.Customer;

/**
 * This will generate the list of Unique customers per GeoZone.
 */
public class UniqueCustomerListPerGeoZoneReport implements Report {

	private static final String REPORT_HEADER = "The list of unique customerId for each geoZone";
	private static final String REPORT_TEXT_FORMAT = "GeoZone: %s Unique Customer Id List: %s";
	
	/**
	 * Generate the reports for the list of unique customerId for each geoZon.
	 * 
	 * @param customers
	 */
	@Override
	public void generateReport(List<Customer> customers) {
		Map<String, Set<Integer>> uniqueCustomerIdsPerGeoZone = uniqueCustomerIdsPerGeoZone(customers);

		System.out.println(REPORT_HEADER);
		uniqueCustomerIdsPerGeoZone.forEach((geoZone, customerIdList) -> System.out
				.println(String.format(REPORT_TEXT_FORMAT, geoZone, customerIdList)));
		System.out.println();

	}

	/**
	 * Create the map of unique CustumerIds for each GeoZone.
	 * 
	 * @param customers
	 * @return Map
	 */
	public Map<String, Set<Integer>> uniqueCustomerIdsPerGeoZone(List<Customer> customers) {
		Map<String, Set<Integer>> uniqueCustomerIdsPerGeoZone = new HashMap<>();
		for (Customer customer : customers) {
			Set<Integer> customerIdSet = uniqueCustomerIdsPerGeoZone.getOrDefault(customer.getGeoZone(),
					new HashSet<>());
			customerIdSet.add(customer.getCustomerId());
			uniqueCustomerIdsPerGeoZone.put(customer.getGeoZone(), customerIdSet);
		}
		return uniqueCustomerIdsPerGeoZone;
	}

}
