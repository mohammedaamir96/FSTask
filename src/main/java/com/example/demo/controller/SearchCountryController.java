package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Countries;
import com.example.demo.service.CountryRepoImpl;

@RestController
public class SearchCountryController {

    @Autowired
    CountryRepoImpl countryRepo;

    @PostMapping("/getcountryname")
    public List<Countries> getcountryname(@RequestBody Countries country) {
        if (country.getName() == null || country.getName().isEmpty()) {
            return countryRepo.findByName(null);
        }
        return countryRepo.findByName(country.getName());
    }

    @PostMapping("/getcountrystate")
    public List<Countries> getcountrystate(@RequestBody Countries country) {
        if (country.getState() == null || country.getState().isEmpty()) {
            return countryRepo.findByState(null);
        }
        return countryRepo.findByState(country.getState());
    }

    @PostMapping("/getcountrynameorstate")
    public List<Countries> getcountrynameorstate(@RequestBody Countries country) {
        if ((country.getState() == null || country.getState().isEmpty()) &&
            (country.getName() == null || country.getName().isEmpty())) {
            return countryRepo.findByNameOrState(null, null);
        }
        return countryRepo.findByNameOrState(country.getName(), country.getState());
    }

    @PostMapping("/getcountrynameorstatelimit")
    public List<Countries> getcountrynameorstatelimit(@RequestBody Countries country) {
        if ((country.getState() == null || country.getState().isEmpty()) &&
            (country.getName() == null || country.getName().isEmpty())) {
            return countryRepo.findByNameOrStateLimit(null, null);
        }
        return countryRepo.findByNameOrStateLimit(country.getName(), country.getState());
    }
}