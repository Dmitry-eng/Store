package com.shop.controller;
//
//import com.shop.model.entity.Employee;

import com.shop.model.entity.Employee;
import com.shop.repository.BranchRepository;
import com.shop.repository.EmployeeRepository;
import com.shop.repository.RoleRepository;
import com.shop.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employee")
public class RestEmployee {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("list")
    public List<Employee> list() {
        return employeeRepository.findAll();
    }
    @PostMapping("list/{value}")
    public List<Employee> list(@PathVariable String value) {
        return employeeRepository.search(value);
    }

    @DeleteMapping(value = "remove/{id}")
    public boolean remove(@PathVariable String id) {
        return employeeService.delete(id);
    }


    @PostMapping(value = "save")
    public String updateEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee).toString();
    }

    @PutMapping("login/{login}")
    public boolean isValidLogin(@PathVariable String login) {
        return employeeService.isValidLogin(login);
    }

    @PutMapping("login/{login}/{uuid}")
    public boolean isValidLogin(@PathVariable String login, @PathVariable String uuid) {
        return employeeService.isValidLoginByUUID(login, uuid);
    }

    @PutMapping("email/{email}")
    public boolean isValidEmail(@PathVariable String email) {
        return employeeService.isValidEmail(email);
    }

    @PutMapping("email/{email}/{uuid}")
    public boolean isValidEmail(@PathVariable String email, @PathVariable String uuid) {
        return employeeService.isValidEmailByUUID(email, uuid);
    }
}
