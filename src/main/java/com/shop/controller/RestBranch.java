package com.shop.controller;

import com.shop.model.entity.Branch;
import com.shop.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class RestBranch {
    @Autowired
    private BranchRepository branchRepository;
    @PutMapping(value = "load")
    public List<Branch> loadBranch() {
        return branchRepository.findAll();
    }
}
