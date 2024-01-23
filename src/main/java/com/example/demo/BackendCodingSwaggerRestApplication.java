package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.FileCopyUtils;

import com.example.demo.pojo.Countries;
import com.example.demo.service.CountryRepoImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
@EnableTransactionManagement
public class BackendCodingSwaggerRestApplication {

	@Autowired CountryRepoImpl countryRepo;
	
    @Bean
    public CommandLineRunner loadData(CountryRepoImpl countryRepo) {
        return args -> {
            Resource resource = new ClassPathResource("data.json");
            byte[] jsonData = FileCopyUtils.copyToByteArray(resource.getInputStream());
            ObjectMapper objectMapper = new ObjectMapper();
            List<Countries> country = objectMapper.readValue(jsonData, new TypeReference<List<Countries>>() {});
            countryRepo.saveAll(country);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendCodingSwaggerRestApplication.class, args);
    }
}