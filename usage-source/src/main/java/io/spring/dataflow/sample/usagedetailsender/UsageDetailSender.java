package io.spring.dataflow.sample.usagedetailsender;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class UsageDetailSender {
    @Value("classpath:data_penggunaan.csv")
    private Resource csvResource;
    
 @Bean
 public Supplier<UsageDetail> sendEvents() {
       List<UsageDetail> usageDetails = loadUsageDetailsFromCSV();
        int[] sentDataCount = {0}; //array dari satu elemen
              
     return () -> {
           if (sentDataCount[0] < usageDetails.size()) {
               UsageDetail usageDetail = usageDetails.get(sentDataCount[0]);
            sentDataCount[0]++;
                return usageDetail;
            } else {
                return null; // Return null to signal the end of data sending
            }
        };
    }
   
    private List<UsageDetail> loadUsageDetailsFromCSV() {
        List<UsageDetail> usageDetails = new ArrayList<>();
        try {
            InputStream inputStream = csvResource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            // Skip header line if needed
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    UsageDetail usageDetail = new UsageDetail();
                    usageDetail.setId(Long.parseLong(parts[0]));
                    usageDetail.setDataUsage(Long.parseLong(parts[1]));
                    usageDetail.setDuration(Long.parseLong(parts[2]));
                    usageDetail.setLocation(parts[3]);
                    usageDetail.setOperatingSystem(parts[4]);  
                    usageDetails.add(usageDetail);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usageDetails;
    }
}
