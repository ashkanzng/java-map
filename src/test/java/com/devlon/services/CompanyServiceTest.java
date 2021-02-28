package com.devlon.services;

import com.devlon.models.Company;
import com.devlon.repositories.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;



@SpringBootTest(classes = {CompanyRepository.class})
@AutoConfigureMockMvc
class CompanyServiceTest {


    @MockBean
    CompanyRepository companyRepository;

    @Test
    void get() {

        Optional<Company> c = companyRepository.findById(1);

        if (c.isPresent()){
            System.out.println(c.get());
        }else {
            System.out.println("empty");

        }

    }
}