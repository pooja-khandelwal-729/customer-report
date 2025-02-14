package com.oracle.customerreport.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.customerreport.entity.Customer;

class UniqueCustomerListPerGeoZoneReportTest {

	private static UniqueCustomerListPerGeoZoneReport uniqueCustomerListPerGeoZoneReport;

	@BeforeAll
	static void init() {
		uniqueCustomerListPerGeoZoneReport = new UniqueCustomerListPerGeoZoneReport();
	}

	@Test
	void getUniqueCustomerIdsPerContractIdTest() {
		// Arrange
		List<Customer> customers = List.of(
				new Customer(2343225, 2345, "us_east", "RedTeam", "ProjectApple", Duration.parse("PT2211s")),
				new Customer(1223456, 2345, "us_west", "BlueTeam", "ProjectBanana", Duration.parse("PT2211s")),
				new Customer(3244332, 2346, "us_west", "YellowTeam3", "ProjectCarrot", Duration.parse("PT4322s")),
				new Customer(3244332, 2346, "eu_west", "YellowTeam3", "ProjectCarrot", Duration.parse("PT4322s")),
				new Customer(33044332, 2346, "eu_west", "YellowTeam3", "ProjectCarrot", Duration.parse("PT4322s")));

		// Act
		Map<String, Set<Integer>> uniqueCustomerIdPerContractIdMap = uniqueCustomerListPerGeoZoneReport
				.uniqueCustomerIdsPerGeoZone(customers);

		// Assert
		assertEquals(3, uniqueCustomerIdPerContractIdMap.size());
		assertEquals(Set.of(2343225), uniqueCustomerIdPerContractIdMap.get("us_east"));
		assertEquals(Set.of(1223456, 3244332), uniqueCustomerIdPerContractIdMap.get("us_west"));
		assertEquals(Set.of(3244332, 33044332), uniqueCustomerIdPerContractIdMap.get("eu_west"));
	}

}
