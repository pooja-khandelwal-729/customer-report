package com.oracle.customerreport.report;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oracle.customerreport.entity.Customer;

/**
 * This will generate the Unique customers per GeoZone.
 */
public class UniqueCustomerCountPerGeoZoneReport implements Report {
	
	private static final String REPORT_HEADER = "The number of unique customerId for each geoZone";
	private static final String REPORT_TEXT_FORMAT = "GeoZone: %s Unique CustomerId count: %s";

	/**
	 * Generate the reports for the number of unique customerId for each geoZones
	 * 
	 * @param customers
	 */
	@Override
	public void generateReport(List<Customer> customers) {
		Map<String, Set<Integer>> uniqueCustomerIdPerGeoZone = uniqueCustomerIdsPerGeoZone(customers);
		System.out.println(REPORT_HEADER);
		uniqueCustomerIdPerGeoZone.forEach((geoZone, customerIdSet) -> System.out
				.println(String.format(REPORT_TEXT_FORMAT, geoZone, customerIdSet.size())));
		System.out.println();
	}

	/**
	 * Create the map of unique CustumerIds for each GeoZone.
	 * 
	 * @param customers
	 * @return Map
	 */
	public Map<String, Set<Integer>> uniqueCustomerIdsPerGeoZone(List<Customer> customers) {
		Map<String, Set<Integer>> uniqueCustomerIdPerGeoZone = new HashMap<>();
		for (Customer customer : customers) {
			Set<Integer> customerIdSet = uniqueCustomerIdPerGeoZone.getOrDefault(customer.getGeoZone(),
					new HashSet<>());
			customerIdSet.add(customer.getCustomerId());
			uniqueCustomerIdPerGeoZone.put(customer.getGeoZone(), customerIdSet);
		}
		return uniqueCustomerIdPerGeoZone;
	}

}
