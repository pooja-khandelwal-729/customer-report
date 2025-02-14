package com.oracle.customerreport.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.customerreport.entity.Customer;

class UniqueCustomerCountPerGeoZoneReportTest {

	private static UniqueCustomerCountPerGeoZoneReport uniqueCustomerCountPerGeoZoneReport;

	@BeforeAll
	static void init() {
		uniqueCustomerCountPerGeoZoneReport = new UniqueCustomerCountPerGeoZoneReport();
	}

	@Test
	void getUniqueCustomerIdPerContractIdTest() {
		// Arrange
		List<Customer> customers = List.of(
				new Customer(2343225, 2345, "us_east", "RedTeam", "ProjectApple", Duration.parse("PT2211s")),
				new Customer(1223456, 2345, "us_west", "BlueTeam", "ProjectBanana", Duration.parse("PT2211s")),
				new Customer(3244332, 2346, "us_west", "YellowTeam3", "ProjectCarrot", Duration.parse("PT4322s")),
				new Customer(3244332, 2346, "eu_west", "YellowTeam3", "ProjectCarrot", Duration.parse("PT4322s")),
				new Customer(33044332, 2346, "eu_west", "YellowTeam3", "ProjectCarrot", Duration.parse("PT4322s")));

		// Act
		Map<String, Set<Integer>> uniqueCustomerIdPerContractIdMap = uniqueCustomerCountPerGeoZoneReport
				.uniqueCustomerIdsPerGeoZone(customers);

		// Assert
		assertEquals(3, uniqueCustomerIdPerContractIdMap.size());
		assertEquals(1, uniqueCustomerIdPerContractIdMap.get("us_east").size());
		assertEquals(2, uniqueCustomerIdPerContractIdMap.get("us_west").size());
		assertEquals(2, uniqueCustomerIdPerContractIdMap.get("eu_west").size());
	}

}
