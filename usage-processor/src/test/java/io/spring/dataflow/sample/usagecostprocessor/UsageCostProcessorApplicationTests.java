package io.spring.dataflow.sample.usagecostprocessor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.MessageConverter;

public class UsageCostProcessorApplicationTests {

   @Test
   public void contextLoads() {
   }

   @Test
   public void testUsageCostProcessor() {
       try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
               TestChannelBinderConfiguration.getCompleteConfiguration(
                       UsageCostProcessorApplication.class)).web(WebApplicationType.NONE)
               .run()) {

           InputDestination source = context.getBean(InputDestination.class);

           UsageDetail usageDetail = new UsageDetail();
           usageDetail.setId(1L);
           usageDetail.setDataUsage(10L);
           usageDetail.setDuration(40L);
           usageDetail.setLocation("Bandung");
           usageDetail.setOperatingSystem("IOS");    
                     

           final MessageConverter converter = context.getBean(CompositeMessageConverter.class);
           Map<String, Object> headers = new HashMap<>();
           headers.put("contentType", "application/json");
           MessageHeaders messageHeaders = new MessageHeaders(headers);
           final Message<?> message = converter.toMessage(usageDetail, messageHeaders);
           source.send(message);
           
           OutputDestination target = context.getBean(OutputDestination.class);
           Message<byte[]> sourceMessage = target.receive(10000);

           final UsageCostDetail usageCostDetail = (UsageCostDetail) converter
                   .fromMessage(sourceMessage, UsageCostDetail.class);
           
           assertThat(usageCostDetail.getDataCost()).isEqualTo(0.5);
           assertThat(usageCostDetail.getCallCost()).isEqualTo(4.0);        
       }
   }
}
