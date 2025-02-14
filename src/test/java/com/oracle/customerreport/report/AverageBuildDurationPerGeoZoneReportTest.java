package com.oracle.customerreport.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.customerreport.entity.Customer;

class AverageBuildDurationPerGeoZoneReportTest {

	private static AverageBuildDurationPerGeoZoneReport averageBuildDurationPerGeoZoneReport;

	@BeforeAll
	static void init() {
		averageBuildDurationPerGeoZoneReport = new AverageBuildDurationPerGeoZoneReport();
	}

	@Test
	void getAverageBuildDurationPerGeoZoneTest() {
		// Arrange
		List<Customer> customers = List.of(
				new Customer(2343225, 2345, "us_east", "RedTeam", "ProjectApple", Duration.parse("PT2211s")),
				new Customer(1223456, 2345, "us_west", "BlueTeam", "ProjectBanana", Duration.parse("PT2211s")),
				new Customer(3244332, 2346, "us_west", "YellowTeam3", "ProjectCarrot", Duration.parse("PT4322s")),
				new Customer(3244332, 2346, "eu_west", "YellowTeam3", "ProjectCarrot", Duration.parse("PT4322s")),
				new Customer(33044332, 2346, "eu_west", "YellowTeam3", "ProjectCarrot", Duration.parse("PT4322s")));

		// Act
		Map<String, Double> uniqueCustomerIdPerContractIdMap = averageBuildDurationPerGeoZoneReport
				.getAverageBuildDurationPerGeoZone(customers);

		// Assert
		assertEquals(3, uniqueCustomerIdPerContractIdMap.size());
		assertEquals(4322.0, uniqueCustomerIdPerContractIdMap.get("eu_west"), 0.1);
		assertEquals(3266.5, uniqueCustomerIdPerContractIdMap.get("us_west"), 0.1);
		assertEquals(2211.0, uniqueCustomerIdPerContractIdMap.get("us_east"), 0.1);
	}
}
