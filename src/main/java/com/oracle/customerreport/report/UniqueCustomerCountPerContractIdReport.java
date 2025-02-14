package com.oracle.customerreport.report;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oracle.customerreport.entity.Customer;

/**
 * This will generate the Unique customers report per Contract Id.
 */
public class UniqueCustomerCountPerContractIdReport implements Report {

	private static final String REPORT_HEADER = "The number of unique customerId for each contractId";
	private static final String REPORT_TEXT_FORMAT = "ContractId: %s Unique CustomerId count: %s";
	
	/**
	 * Generate the reports for the number of unique customerId for each contractId.
	 * 
	 * @param customers
	 */
	@Override
	public void generateReport(List<Customer> customers) {
		Map<Integer, Set<Integer>> uniqueCustomerIdPerContractId = getUniqueCustomerIdsPerContractId(customers);
		System.out.println(REPORT_HEADER);
		uniqueCustomerIdPerContractId.forEach((contractId, customerIdSet) -> System.out
				.println(String.format(REPORT_TEXT_FORMAT, contractId, customerIdSet.size())));
		System.out.println();
	}

	/**
	 * Create the map of unique CustumerIds for each Contract id.
	 * 
	 * @param customers
	 * @return Map
	 */
	public Map<Integer, Set<Integer>> getUniqueCustomerIdsPerContractId(List<Customer> customers) {
		Map<Integer, Set<Integer>> uniqueCustomerIdPerContractId = new HashMap<>();

		for (Customer customer : customers) {
			Set<Integer> customerIdSet = uniqueCustomerIdPerContractId.getOrDefault(customer.getContractId(),
					new HashSet<>());
			customerIdSet.add(customer.getCustomerId());
			uniqueCustomerIdPerContractId.put(customer.getContractId(), customerIdSet);
		}
		return uniqueCustomerIdPerContractId;
	}

}
