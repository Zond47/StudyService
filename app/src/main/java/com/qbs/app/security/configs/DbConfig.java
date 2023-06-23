package com.qbs.app.security.configs;

import com.qbs.app.domain.AppUser;
import com.qbs.app.domain.Comment;
import com.qbs.app.domain.Post;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class DbConfig {

  public static SessionFactory getSessionFactory() {

    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(dbSettings())
            .build();

    Metadata metadata = new MetadataSources(serviceRegistry)
            .addAnnotatedClass(AppUser.class)
            .addAnnotatedClass(Post.class)
            .addAnnotatedClass(Comment.class)
            // other domain classes
            .buildMetadata();

    return metadata.buildSessionFactory();
  }

  private static Map<String, String> dbSettings() {
    Map<String, String> settings = new HashMap<>();
    settings.put("connection.driver_class", "org.h2.Driver");
    settings.put("dialect", "org.hibernate.dialect.H2Dialect");
    settings.put("hibernate.connection.username", "sa");
    settings.put("hibernate.connection.password", "amAnda1092");
    settings.put("hibernate.current_session_context_class", "thread");
    settings.put("hibernate.show_sql", "true");
    settings.put("hibernate.format_sql", "true");
    return settings;
  }
}
