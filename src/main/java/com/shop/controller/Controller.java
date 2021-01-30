package com.shop.controller;

import com.shop.repository.BranchRepository;
import com.shop.repository.EmployeeRepository;
import com.shop.repository.RoleRepository;
import com.shop.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }



    @RequestMapping(value = "discount")
    public String discount() {
        return "discount";
    }

    @RequestMapping(value = "client")
    public String client() {
        return "client";
    }


    @RequestMapping(value = "employee")
    public String employee() {
        return "employee";
    }

    @RequestMapping(value = "newEmployee")
    public String newEmployee() {
        return "newEmployee";
    }
    @RequestMapping(value = "product")
    public String product(){
        return "product";
    }

    @RequestMapping(value = "newEmployee/{uuid}")
    public String newEmployee(Model model, @PathVariable String uuid) {
        model.addAttribute("employee", employeeService.getEmployeeToString(uuid));
        model.addAttribute("branch", branchRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return "newEmployee";
    }
}
