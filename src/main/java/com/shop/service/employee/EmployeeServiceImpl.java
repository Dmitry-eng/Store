package com.shop.service.employee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shop.config.HibernateProxyTypeAdapter;
import com.shop.model.entity.Employee;
import com.shop.model.entity.User;
import com.shop.repository.EmployeeRepository;
import com.shop.repository.UserRepository;
import com.shop.service.app.DataHandler;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DataHandler dataHandler;
    private static final GsonBuilder gsonBuilder;

    static {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
    }

    @Override
    public JSONObject save(Employee user) {
        JSONObject jsonObject = new JSONObject();
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            employeeRepository.save(user);
            jsonObject.put("result", true);
            jsonObject.put("uuid", user.getId());
        } catch (Exception e) {
            jsonObject.put("result", false);
        }
        return jsonObject;
    }

    @Override
    public boolean delete(String id) {
        try {
            UUID uuid = converterStringToUUID(id);
            employeeRepository.deleteById(uuid);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidLogin(String login) {
        String sql = "SELECT login FROM \"user\" where login =:login";
        Map<String, Object> params = new HashMap<>();
        params.put("login", login);
        List<Object> objects = dataHandler.getResult(sql, params);
        return objects.size() == 0;
    }

    @Override
    public boolean isValidEmail(String email) {
        String sql = "SELECT login FROM \"user\" where email =:email";
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        List<Object> objects = dataHandler.getResult(sql, params);
        return objects.size() == 0;
    }

    @Override
    public boolean isValidLoginByUUID(String login, String id) {
        String sql = "SELECT Cast(id as varchar) id, login FROM \"user\" where login =:login";
        Map<String, Object> params = new HashMap<>();
        params.put("login", login);
        List<Object> objects = dataHandler.getResult(sql, params);
        return objects.size() == 0 || objects.contains(id);
    }

    @Override
    public boolean isValidEmailByUUID(String email, String id) {
        String sql = "SELECT Cast(id as varchar) id, login FROM \"user\" where email =:email";
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        List<Object> objects = dataHandler.getResult(sql, params);
        return objects.size() == 0 || objects.contains(id);
    }

    @Override
    public String getEmployeeToString(String id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Employee employee = employeeRepository.getOne(Objects.requireNonNull(converterStringToUUID(id)));
        Gson gson = gsonBuilder.create();
        JsonElement jsonElement = gson.toJsonTree(employee);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if (employee.getBirthDate() != null) {
            jsonObject.addProperty("birthDate", simpleDateFormat.format(employee.getBirthDate()));
        }
        return jsonObject.toString();
    }

    private boolean isValidForm(User employee, UUID uuid) {
        if (employee == null) {
            return true;
        } else if (uuid == null) {
            return false;
        }
        return employee.getId().equals(uuid);
    }

    private UUID converterStringToUUID(String uuid) {
        if (uuid == null) {
            return null;
        }
        try {
            return UUID.fromString(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}