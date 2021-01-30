package com.shop.controller;

import com.shop.model.entity.Role;
import com.shop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RestRole {
    @Autowired
    private RoleRepository roleRepository;
    @PutMapping(value = "load")
    public List<Role> loadRole() {
        return roleRepository.findAll();
    }

}
