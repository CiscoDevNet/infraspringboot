package com.cisco.devnet.example.configs;

import com.cisco.devnet.infracheck.InfraCheck;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public InfraCheck infraCheck() {
        return new InfraCheck();
    }

//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
//        jsonConverter.setObjectMapper(objectMapper);
//
//        return jsonConverter;
//    }
}