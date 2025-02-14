/**
 * 
 */
package com.oracle.customerreport.entity;

import java.time.Duration;

/**
 * Customer entity having attributes related to customer-data
 */
public class Customer {

	/**
	 * @param customerId
	 * @param contractId
	 * @param geoZone
	 * @param teamCode
	 * @param projectCode
	 * @param buildDuration
	 */
	public Customer(int customerId, int contractId, String geoZone, String teamCode, String projectCode,
			Duration buildDuration) {
		super();
		this.customerId = customerId;
		this.contractId = contractId;
		this.geoZone = geoZone;
		this.teamCode = teamCode;
		this.projectCode = projectCode;
		this.buildDuration = buildDuration;
	}

	private int customerId;

	private int contractId;

	private String geoZone;

	private String teamCode;

	private String projectCode;

	private Duration buildDuration;

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the contractId
	 */
	public int getContractId() {
		return contractId;
	}

	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return the geoZone
	 */
	public String getGeoZone() {
		return geoZone;
	}

	/**
	 * @param geoZone the geoZone to set
	 */
	public void setGeoZone(String geoZone) {
		this.geoZone = geoZone;
	}

	/**
	 * @return the teamCode
	 */
	public String getTeamCode() {
		return teamCode;
	}

	/**
	 * @param teamCode the teamCode to set
	 */
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	/**
	 * @return the projectCode
	 */
	public String getProjectCode() {
		return projectCode;
	}

	/**
	 * @param projectCode the projectCode to set
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	/**
	 * @return the buildDuration
	 */
	public Duration getBuildDuration() {
		return buildDuration;
	}

	/**
	 * @param buildDuration the buildDuration to set
	 */
	public void setBuildDuration(Duration buildDuration) {
		this.buildDuration = buildDuration;
	}

}
