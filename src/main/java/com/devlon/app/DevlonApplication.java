package com.devlon.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.devlon.*"})
@EnableJpaRepositories("com.devlon.repositories")
@EntityScan("com.devlon.models")
@EnableCaching
@SpringBootApplication
public class DevlonApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevlonApplication.class, args);
		long jvmPid = ProcessHandle.current().pid();

		System.out.println("---------------------  System is running on htt://localhost:8080  -------------------");
		System.out.println("---------------------  Get all htt://localhost:8080/api/company/all  ---------------------");
		System.out.println("---------------------  JVM process Id" + jvmPid +"  ---------------------------------------");
	}

}
