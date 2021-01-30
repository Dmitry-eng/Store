package com.shop.service.app;

import com.shop.config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.*;

@Service
public class DataHandlerImpl implements DataHandler {
    @Override
    public List<Object> getResult(String sql, Map<String, Object> params) {
        List result = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            initParams(query, params);
            result = query.getResultList();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Object> arraysResult = new ArrayList();
        for (Object value : result) {
            if (value != null) {
                if (value instanceof Object[]) {
                    arraysResult.addAll(Arrays.asList((Object[]) value));
                } else {
                    arraysResult.addAll(Arrays.asList(value));
                }
            }
        }
        return arraysResult;
    }

    @Override
    public List<Object> getResult(String sql) {
        return getResult(sql, null);
    }

    private void initParams(Query query, Map<String, Object> params) {
        if (params == null) {
            return;
        }
        Set entries = params.entrySet();
        Iterator iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            query.setParameter((String) entry.getKey(), entry.getValue());
        }
    }
}