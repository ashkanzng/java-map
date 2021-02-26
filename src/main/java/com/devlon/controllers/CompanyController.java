package com.devlon.controllers;

import com.devlon.models.Company;
import com.devlon.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(companyService.get(id));
        }catch (EntityNotFoundException entityNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
        }
    }

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Company addCompany(@RequestBody Company company) {
        return companyService.create(company);
    }

    @PostMapping(value = "/update/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> updateCompany(@PathVariable Integer id, @RequestBody Company company) {
        try{
            return ResponseEntity.ok().body(companyService.update(id, company));
        }catch (EntityNotFoundException entityNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public Boolean removeCompany(@PathVariable Integer id) {
        return companyService.delete(id);
    }

}