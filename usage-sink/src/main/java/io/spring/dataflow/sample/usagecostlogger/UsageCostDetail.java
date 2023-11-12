package io.spring.dataflow.sample.usagecostlogger;

public class UsageCostDetail {
	private long Id;
	private double dataCost;
	private double callCost;
	private String location;
	private String operatingSystem;
	private String category;

    public UsageCostDetail() {
    }
    
    
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
    public String toString() {
        return "{\"id\": \"" + this.getId() + "\", \"dataCost\": \"" + 
        			this.getDataCost() + "\", \"callCost\": \"" + this.getCallCost() + "\", \"location\": \"" + this.getLocation() +"\", \"operatingSystem\": \"" + this.getOperatingSystem() + "\"}";
    }
}
