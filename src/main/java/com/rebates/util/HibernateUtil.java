package com.rebates.util;


import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class HibernateUtil {
    private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory;
    private HibernateUtil(){};


    static {
        try {
            sessionFactory = loadSessionFactory();
        }catch (Exception e){
            logger.error("Exception while initializing hibernate util...");
            e.printStackTrace();
        }
    }

    private static SessionFactory loadSessionFactory(){
//        String dbDriver = "org.postgresql.Driver";
//        String dbDialect = "org.hibernate.dialect.PostgreSQL9Dialect";
//        String dbUrl = "jdbc:postgresql://localhost:5430/ascending-14";
//        String dbUser = "admin";
//        String dbPassword = "password";
        String dbDriver = System.getProperty("database.driver");
        String dbDialect = System.getProperty("database.dialect");
        String dbUrl = System.getProperty("database.url");
        String dbUser = System.getProperty("database.user");
        String dbPassword = System.getProperty("database.password");
        String[] modelPackages = {"com.rebates.model"};

        //create Java based Hibernate Configurations
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER,dbDriver);
        settings.put(Environment.DIALECT,dbDialect);
        settings.put(Environment.URL,dbUrl);
        settings.put(Environment.USER,dbUser);
        settings.put(Environment.PASS,dbPassword);
        settings.put(Environment.SHOW_SQL,"true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
        settings.put(Environment.HBM2DDL_AUTO,"validate");
        configuration.setProperties(settings);

        //mapping
        EntityScanner.scanPackages(modelPackages).addTo(configuration);//find "@Entity" to scan and add to configuration
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        ServiceRegistry serviceRegistry = registryBuilder.applySettings(configuration.getProperties()).build();
        SessionFactory localSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return localSessionFactory;
    }
    public static SessionFactory getSessionFactory(){
        return loadSessionFactory();
    }

    public static Session getSession() throws HibernateException {
        Session retSession = null;
        try {
            retSession = sessionFactory.openSession();
        }catch(Throwable t){
            logger.error("Exception while getting session.. ");
            t.printStackTrace();
        }
        if(retSession == null) {
            logger.error("session is discovered null");
        }
        return retSession;
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        logger.info("Success generate sessionFactory ={}", sessionFactory.toString());
        logger.info("Success generate sessionFactory, hashcode={}", sessionFactory.hashCode());
        Session session = sessionFactory.openSession();
        //        Session session1 = sessionFactory.openSession();
        logger.info("The end");
        session.close();

    }

}





