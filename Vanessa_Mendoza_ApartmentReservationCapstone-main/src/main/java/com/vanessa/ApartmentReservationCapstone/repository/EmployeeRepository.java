package com.vanessa.ApartmentReservationCapstone.repository;

import com.vanessa.ApartmentReservationCapstone.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByFullName(String fullName);

    boolean existsByFullName(String fullName);

}

