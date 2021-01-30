package com.shop.repository;

import com.shop.model.entity.Employee;
import com.shop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Employee, UUID> {
    @Query("SELECT user FROM  Employee user where user.login=:login")
    User getByLogin(@Param(value = "login") String login);
    @Query("SELECT user FROM  Employee user where user.email=:email ")
    User getByEmail(@Param(value = "email") String email);
}
