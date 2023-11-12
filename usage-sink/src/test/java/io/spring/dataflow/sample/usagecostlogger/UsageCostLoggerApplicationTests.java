//package io.spring.dataflow.sample.usagecostlogger;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.awaitility.Awaitility;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.test.system.CapturedOutput;
//import org.springframework.boot.test.system.OutputCaptureExtension;
//import org.springframework.cloud.stream.binder.test.InputDestination;
//import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageHeaders;
//import org.springframework.messaging.converter.CompositeMessageConverter;
//import org.springframework.messaging.converter.MessageConverter;
//
//@ExtendWith(OutputCaptureExtension.class)
//public class UsageCostLoggerApplicationTests {
//
//	@Test
//	public void testUsageCostLogger(CapturedOutput output) {
//	    try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
//	            TestChannelBinderConfiguration
//	                    .getCompleteConfiguration(UsageCostLoggerApplication.class))
//	            .web(WebApplicationType.NONE)
//	            .run()) {
//
//	        InputDestination source = context.getBean(InputDestination.class);
//
//	        UsageCostDetail usageCostDetail = new UsageCostDetail();
//	        usageCostDetail.setId(1);
//	        usageCostDetail.setOperatingSystem("IOS");
//	        usageCostDetail.setCallCost(5.0); // Fixed value
//	        usageCostDetail.setDataCost(4.0); // Fixed value
//	        usageCostDetail.setLocation("Jakarta");
//
//	        final MessageConverter converter = context.getBean(CompositeMessageConverter.class);
//	        Map<String, Object> headers = new HashMap<>();
//	        headers.put("contentType", "application/json");
//	        MessageHeaders messageHeaders = new MessageHeaders(headers);
//	        final Message<?> message = converter.toMessage(usageCostDetail, messageHeaders);
//
//	        source.send(message);
//
//	        String expectedOutput = "{\"id\": 1, \"dataCost\": 4.0, \"callCost\": 5.0, \"location\": \"Jakarta\", \"operatingSystem\": \"IOS\"}";
//	        Awaitility.await()
//	        .atMost(Duration.ofSeconds(20)) // Increase the timeout duration
//	        .until(output::getAll, value -> value.contains(expectedOutput));
//	        System.out.println("Captured Output: " + output.getAll());
//	    }
//	   
//
//	}
//}
