package com.vanessa.ApartmentReservationCapstone;

import com.vanessa.ApartmentReservationCapstone.employee.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration

@ComponentScan
public class ApartmentReservationCapstoneApplication {



	public static void main(String[] args) {
		SpringApplication.run(ApartmentReservationCapstoneApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(EmployeeService cemployeeService) {
		return args -> System.out.println("My application got started!!");
	}

}
