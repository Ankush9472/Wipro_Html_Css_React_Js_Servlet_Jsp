package com.lms.config;

import com.lms.entity.Course;
import com.lms.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                
                configuration.setProperty("hibernate.connection.driver_class",            // Database connection
                        "com.mysql.cj.jdbc.Driver");
                configuration.setProperty("hibernate.connection.url",
                        "jdbc:mysql://localhost:3306/lms_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC");
                
                configuration.setProperty("hibernate.connection.username", "root");
                
                configuration.setProperty("hibernate.connection.password", "tiger");  

               
                configuration.setProperty("hibernate.dialect",          // Hibernate settings
                        "org.hibernate.dialect.MySQLDialect");
                
                
                configuration.setProperty("hibernate.hbm2ddl.auto", "update");
                   configuration.setProperty("hibernate.show_sql", "true");
                  configuration.setProperty("hibernate.format_sql", "true");

                configuration.addAnnotatedClass(Student.class);
                 configuration.addAnnotatedClass(Course.class);

                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                          .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(registry);
                System.out.println("✓ Hibernate SessionFactory created");

            } catch (Exception e) {
                System.err.println(" Error creating SessionFactory: " + e.getMessage());
                e.printStackTrace();
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) sessionFactory.close();
    }
}