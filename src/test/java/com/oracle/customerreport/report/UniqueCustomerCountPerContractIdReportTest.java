package com.oracle.customerreport.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.oracle.customerreport.entity.Customer;

class UniqueCustomerCountPerContractIdReportTest {

	private UniqueCustomerCountPerContractIdReport report;

	@BeforeEach
	void setUp() {
		report = new UniqueCustomerCountPerContractIdReport();
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
		Map<Integer, Set<Integer>> uniqueCustomerIdPerContractIdMap = report
				.getUniqueCustomerIdsPerContractId(customers);

		// Assert
		assertEquals(2, uniqueCustomerIdPerContractIdMap.size());
		assertEquals(2, uniqueCustomerIdPerContractIdMap.get(2345).size());
		assertEquals(2, uniqueCustomerIdPerContractIdMap.get(2346).size());
	}

}
