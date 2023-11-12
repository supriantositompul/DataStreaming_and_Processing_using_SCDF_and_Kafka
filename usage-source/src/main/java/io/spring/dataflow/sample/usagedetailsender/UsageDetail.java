package io.spring.dataflow.sample.usagedetailsender;

public class UsageDetail {

	public UsageDetail() { }
    private Long id;
    private long dataUsage;
    private long duration;
    private String location;
    private String operatingSystem;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getDataUsage() {
		return dataUsage;
	}
	public void setDataUsage(long dataUsage) {
		this.dataUsage = dataUsage;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
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
}
