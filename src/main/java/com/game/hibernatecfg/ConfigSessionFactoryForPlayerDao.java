package com.game.hibernatecfg;

import com.game.entity.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class ConfigSessionFactoryForPlayerDao {
    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        Properties properties =
                new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/projectHibernateJRU");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "admin");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");

        sessionFactory = configuration
                .setProperties(properties)
                .addAnnotatedClass(Player.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void shutdownSessionFactory() {
        sessionFactory.close();
    }
}
