package io.spring.dataflow.sample.usagecostlogger;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsageCostLogger {
	@Value("${test.string}")
	private String testString;

	private static final Logger logger = LoggerFactory.getLogger(UsageCostLoggerApplication.class);

	@Bean
	public Consumer<UsageCostDetail> process() {
		return usageCostDetail -> {
			logger.info(usageCostDetail.toString());
			logger.info("o>> TEST CONF {}", testString);
		};
	}
}
