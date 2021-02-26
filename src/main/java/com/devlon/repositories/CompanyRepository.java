package com.devlon.repositories;

import com.devlon.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    List<Company> findByParentId(Integer id);
}
