package com.geo.services;

import com.geo.app.Application;
import com.geo.models.Company;
import com.geo.transactionObjects.CompanyT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = {Application.class})
class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @PersistenceContext
    EntityManager entityManager;

    // Test company with children
    @Test
    @Transactional
    void get() {

        Company company = new Company();
        company.setName("Parent");
        company.setStations(new HashSet<>());
        Company parentCompany = companyService.create(company);


        Company child_company = new Company();
        child_company.setName("Child 1");
        child_company.setStations(new HashSet<>());
        child_company.setParentId(company.getId());
        companyService.create(child_company);

        Company child_2_company = new Company();
        child_2_company.setName("Child 2");
        child_2_company.setStations(new HashSet<>());
        child_2_company.setParentId(company.getId());
        companyService.create(child_2_company);


        CompanyT tree = companyService.get(parentCompany.getId());
        System.out.println(tree.getChild().size());
        assertEquals(tree.getChild().size(),2);

    }
}