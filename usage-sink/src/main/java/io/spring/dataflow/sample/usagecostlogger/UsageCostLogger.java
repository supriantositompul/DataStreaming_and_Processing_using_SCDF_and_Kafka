package io.spring.dataflow.sample.usagecostlogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;       

@Configuration
public class UsageCostLogger {
    private static final Logger logger = LoggerFactory.getLogger(UsageCostLogger.class);

    @Autowired
    private CategoryRepository categoryRepository;
    private final List<UsageCostDetail> usageCostDetails = new ArrayList<>();
    @Value("${output.json.directory:D:\\scdf1}")
    private String outputJsonDirectory;

    @Bean
    public Consumer<UsageCostDetail> process() {
        return usageCostDetail -> {
            logger.info(usageCostDetail.toString());

            double dataCost = usageCostDetail.getDataCost();
//            double callCost = usageCallCost.getCallCost();
            String category = getCategoryForDataCost(dataCost);

            usageCostDetail.setCategory(category);

            // Append category to existing features
            usageCostDetail.setId(usageCostDetail.getId());
            usageCostDetail.setCallCost(usageCostDetail.getCallCost());
            usageCostDetail.setLocation(usageCostDetail.getLocation());
            usageCostDetail.setOperatingSystem(usageCostDetail.getOperatingSystem());

            // Save to JSON
            usageCostDetails.add(usageCostDetail);
            if (usageCostDetails.size() >= 50) {
                saveToJsonFile(usageCostDetails);
                usageCostDetails.clear();
            }
        };
    } 
   
    private String getCategoryForDataCost(double dataCost) {
        List<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            if (dataCost >= category.getMinDataCost() && dataCost < category.getMaxDataCost()) {
                return category.getCategoryName();
            }
        }
        return "New Member";
    }
    
//    private String getCategoryForCallCost(double callCost) {
//    	List<Category> categories = categoryRepository.findAll();
//    	
//    	for (Category category : categories) {
//    		if(callCost >= category.getMinDataCost() && callCost < category.getMaxDataCost()) {
//    			return category.getCategoryName(); 
//    			
//    		}
//    	}
//    	return "New Member"; 
//    }
    
    private void saveToJsonFile(List<UsageCostDetail> usageCostDetails) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonFilePath = outputJsonDirectory + "/usage_cost_data_" + System.currentTimeMillis() + ".json";
        try {
            List<Object> jsonOutput = new ArrayList<>();
            for (UsageCostDetail detail : usageCostDetails) {
                Map<String, Object> detailMap = new HashMap<>();
                detailMap.put("id", detail.getId());
                detailMap.put("dataCost", detail.getDataCost());
                detailMap.put("callCost", detail.getCallCost());
                detailMap.put("location", detail.getLocation());
                detailMap.put("operating system", detail.getOperatingSystem());
                detailMap.put("category", detail.getCategory());
                jsonOutput.add(detailMap);
            }
            objectMapper.writeValue(new File(jsonFilePath), jsonOutput);
            logger.info("Data saved to JSON file: " + jsonFilePath);
        } catch (IOException e) {
            logger.error("Error saving data to JSON file: " + e.getMessage());
        }
    }
}




