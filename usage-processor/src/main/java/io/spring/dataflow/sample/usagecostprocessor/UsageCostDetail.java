package io.spring.dataflow.sample.usagecostprocessor;

public class UsageCostDetail {
	
	public UsageCostDetail() { }
	private long Id;
	private double dataCost;
	private double callCost;
	private String location;
	private String operatingSystem;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public double getDataCost() {
		return dataCost;
	}
	public void setDataCost(double dataCost) {
		this.dataCost = dataCost;
	}
	public double getCallCost() {
		return callCost;
	}
	public void setCallCost(double callCost) {
		this.callCost = callCost;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String toString() {
		return "{\"Id\": \""+this.getId() + "\", \"dataCost\": \""+ this.getDataCost()+"\", \"callCost\": \"" + this.getCallCost()+ "\" }";
	}
}
