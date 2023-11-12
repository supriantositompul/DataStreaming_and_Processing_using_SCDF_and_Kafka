package io.spring.dataflow.sample.usagedetailsender;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.MessageConverter;


public class UsageDetailSenderApplicationTests {
	@Test
	public void contextLoads() {
	}

	@Test
	public void testUsageDetailSender() {
		try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
				TestChannelBinderConfiguration
						.getCompleteConfiguration(UsageDetailSenderApplication.class))
				.web(WebApplicationType.NONE)
				.run()) {
			
			OutputDestination target = context.getBean(OutputDestination.class);
			Message<byte[]> sourceMessage = target.receive(1000);
			
			final MessageConverter converter = context.getBean(CompositeMessageConverter.class);
			UsageDetail usageDetail = (UsageDetail) converter
					.fromMessage(sourceMessage, UsageDetail.class);

			assertThat(usageDetail.getId()).isBetween(1L, 60L);
			assertThat(usageDetail.getDataUsage()).isBetween(0L, 700L);
			assertThat(usageDetail.getDuration()).isBetween(0L, 260L);
			assertThat(usageDetail.getLocation()).isEqualTo("Jakarta");
			assertThat(usageDetail.getOperatingSystem()).isEqualTo("IOS");
		}
	}
}
