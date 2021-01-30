package com.shop.repository;

import com.shop.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("SELECT employee FROM  Employee employee where (employee.login like %:value%) or (employee.name like %:value%) " +
            "or (employee.middleName like %:value%) or (employee.surname like %:value%) or (employee.email like %:value%) " +
            "or (employee.phoneNumber like %:value%)")
    List<Employee> search(@Param(value = "value") String value);
}
