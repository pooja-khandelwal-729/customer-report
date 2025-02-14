package com.oracle.customerreport.report;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oracle.customerreport.entity.Customer;

/**
 * This will generate average Build Duration per GeoZone.
 */
public class AverageBuildDurationPerGeoZoneReport implements Report {

	private static final String REPORT_HEADER = "The average buildduration for each geoZone";
	private static final String REPORT_TEXT_FORMAT = "GeoZone: %s Average build duration: %ss";

	/**
	 * Generate the reports for the average build duration for each GeoZone for
	 * given list of users.
	 * 
	 * @param customers
	 */
	@Override
	public void generateReport(List<Customer> customers) {
		Map<String, Double> buildDurationListPerGeoZone = getAverageBuildDurationPerGeoZone(customers);
		System.out.println(REPORT_HEADER);
		buildDurationListPerGeoZone.forEach((geoZone, averageBuildDuration) -> System.out
				.println(String.format(REPORT_TEXT_FORMAT, geoZone, averageBuildDuration)));
		System.out.println();

	}

	/**
	 * Generate the map of average Build duration per GeoZone.
	 * 
	 * @param customers
	 * @return Map
	 */
	public Map<String, Double> getAverageBuildDurationPerGeoZone(List<Customer> customers) {
		Map<String, List<Duration>> buildDurationListPerGeoZone = new HashMap<>();

		for (Customer customer : customers) {
			List<Duration> buildDurationList = buildDurationListPerGeoZone.getOrDefault(customer.getGeoZone(),
					new ArrayList<>());
			buildDurationList.add(customer.getBuildDuration());
			buildDurationListPerGeoZone.put(customer.getGeoZone(), buildDurationList);
		}

		Map<String, Double> averageBuildDurationPerGeoZone = new HashMap<>();
		buildDurationListPerGeoZone.forEach((geoZone, buildDurationList) -> averageBuildDurationPerGeoZone.put(geoZone,
				getBuildDurationAverage(buildDurationList)));

		return averageBuildDurationPerGeoZone;

	}

	/**
	 * Generate the average of Build Duration List
	 * 
	 * @param buildDurationList
	 * @return double
	 */
	private double getBuildDurationAverage(List<Duration> buildDurationList) {
		return buildDurationList.stream().mapToLong(c -> c.getSeconds()).average().orElse(0.0);
	}

}
