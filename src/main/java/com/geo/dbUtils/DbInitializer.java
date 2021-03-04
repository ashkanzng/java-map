package com.geo.dbUtils;

import com.geo.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    CompanyService companyService;

    @Override
    public void run(String... args){
    }
}
