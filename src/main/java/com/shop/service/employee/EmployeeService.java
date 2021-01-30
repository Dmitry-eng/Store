package com.shop.service.employee;

import com.shop.model.entity.Employee;
import org.json.JSONObject;

import java.util.Set;

public interface EmployeeService {
    JSONObject save(Employee user);
    boolean delete(String id);
    boolean isValidLogin(String login);
    boolean isValidEmail(String email);
    boolean isValidLoginByUUID(String login, String uuid);
    boolean isValidEmailByUUID(String email, String uuid);
    String getEmployeeToString(String id);
}
