package com.shop.config;

import com.shop.model.entity.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
//                registry = new StandardServiceRegistryBuilder().configure().build();
//                MetadataSources sources = new MetadataSources(registry);
//                Metadata metadata = sources.getMetadataBuilder().build();
//                sessionFactory = metadata.getSessionFactoryBuilder().build();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                if (registry != null) {
//                    StandardServiceRegistryBuilder.destroy(registry);
//                }
//            }
                Configuration configuration = new Configuration();
                Properties setting = new Properties();
                // настройки
                setting.setProperty(Environment.DRIVER, "org.postgresql.Driver");
                setting.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/database");
                setting.setProperty(Environment.USER, "root");
                setting.setProperty(Environment.PASS, "root");
                setting.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
                setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

//
//configuration.addAnnotatedClass(Employee.class);

                configuration.setProperties(setting);



                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
