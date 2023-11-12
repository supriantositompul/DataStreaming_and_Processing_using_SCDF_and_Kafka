package io.spring.dataflow.sample.usagecostlogger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;
    private double minDataCost;
    private double maxDataCost;

    public Category() {
        
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public double getMinDataCost() {
		return minDataCost;
	}
	public void setMinDataCost(double minDataCost) {
		this.minDataCost = minDataCost;
	}
	public double getMaxDataCost() {
		return maxDataCost;
	}
	public void setMaxDataCost(double maxDataCost) {
		this.maxDataCost = maxDataCost;
	}
}





    

