package com.devlon.services;

import com.devlon.models.Company;
import com.devlon.models.Station;
import com.devlon.repositories.CompanyRepository;
import com.devlon.repositories.StationRepository;
import com.devlon.transactionObjects.CompanyT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    StationRepository stationRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    public Company update(int id, Company company) {
        Company existingCompany = companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Company not found"));
        if (company.getStations() != null && company.getStations().size() > 0) {
            stationRepository.deleteAll(existingCompany.getStations());
            existingCompany.setStations(company.getStations());
        }
        if (company.getName() != null) existingCompany.setName(company.getName());
        if (company.getParentId() != null) existingCompany.setParentId(company.getParentId());
        companyRepository.save(existingCompany);
        return existingCompany;
    }

    public boolean delete(int id) {
        Company existingCompany = companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Company not found"));
        companyRepository.delete(existingCompany);
        return true;
    }

    // Get company with all the children stations in the tree
    public CompanyT get(Integer id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Company not found"));
        //Store id to prevent loop
        List<Integer> companyIdes = new ArrayList<>();
        Set<Station> stations = new HashSet<>(company.getStations());
        CompanyT companyTransactionObject = new CompanyT(company.getName());
        companyIdes.add(company.getId());
        findChild(company, companyTransactionObject, stations, companyIdes);
        companyTransactionObject.setStations(stations);
        return companyTransactionObject;
    }

    // Recursive function to find all children of companies
    void findChild(Company company, CompanyT companyTransactionObject, Set<Station> stations, List<Integer> companyIdes) {
        List<Company> children = companyRepository.findAllByParentId(company.getId());
        for (Company child : children) {
            if (!companyIdes.contains(child.getId())) {
                companyIdes.add(child.getId());
                stations.addAll(child.getStations());
                CompanyT companyTChild = new CompanyT(child.getName());
                companyTChild.setStations(child.getStations());
                companyTransactionObject.getChild().add(companyTChild);
                findChild(child, companyTChild, stations, companyIdes);
            }
        }
    }
}
