package com.shop.repository;

import com.shop.model.entity.Branch;
import com.shop.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
