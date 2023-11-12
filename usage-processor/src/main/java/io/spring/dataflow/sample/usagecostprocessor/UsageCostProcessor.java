package io.spring.dataflow.sample.usagecostprocessor;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsageCostProcessor {

	private double ratePerMB = 100;
	private double ratePerSecond = 0.1;
//	private double ratePerRange = 0.5;
		
	@Bean
	public Function<UsageDetail, UsageCostDetail> processUsageCost() {
		return usageDetail -> {
			UsageCostDetail usageCostDetail = new UsageCostDetail();
			usageCostDetail.setId(usageDetail.getId());
			usageCostDetail.setDataCost(usageDetail.getDataUsage() * this.ratePerMB);
			usageCostDetail.setCallCost(usageDetail.getDuration() * this.ratePerSecond );
			usageCostDetail.setLocation(usageDetail.getLocation());
			usageCostDetail.setOperatingSystem(usageDetail.getOperatingSystem());
			return usageCostDetail;
		};
	}
}





