package com.shop.service.app;

import java.util.List;
import java.util.Map;

public interface DataHandler {
    List<Object> getResult(String sql, Map<String, Object> params);
    List<Object> getResult(String sql);
}
