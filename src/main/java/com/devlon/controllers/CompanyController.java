package com.devlon.controllers;

import com.devlon.models.Company;
import com.devlon.models.Station;
import com.devlon.services.CompanyService;
import com.devlon.transactionObjects.CompanyT;
import com.fasterxml.jackson.databind.JsonNode;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(allowCredentials = "false", origins = "*", allowedHeaders = "*", maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}
)
@RequestMapping(value = "/api/company")
@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/all")
    public List<Company> all() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public Company find(@PathVariable Integer id) {
        return companyService.findById(id);
    }

    @GetMapping("/get/{id}")
    public CompanyT get(@PathVariable Integer id) {
        return companyService.get(id);
    }

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Company addCompany(@RequestBody Company company) {
        return companyService.create(company);
    }

    @PostMapping(value = "/update/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Company updateCompany(@PathVariable Integer id, @RequestBody Company company) {
        return companyService.update(id, company);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Boolean removeCompany(@PathVariable Integer id) {
        return companyService.delete(id);
    }

}