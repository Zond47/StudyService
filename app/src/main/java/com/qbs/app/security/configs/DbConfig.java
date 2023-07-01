package com.qbs.app.security.configs;

import com.qbs.app.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;import java.util.HashMap;
import java.util.Map;

@Configuration
public class DbConfig {

  public static SessionFactory getSessionFactory() {

    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(dbSettings())
            .build();

    Metadata metadata =
        new MetadataSources(serviceRegistry)
            .addAnnotatedClass(AppUser.class)
            .addAnnotatedClass(Post.class)
            .addAnnotatedClass(Comment.class)
            .addAnnotatedClass(UserPostId.class)
            .addAnnotatedClass(UserPost.class)
            // other domain classes
            .buildMetadata();

    return metadata.buildSessionFactory();
  }

  private static Map<String, String> dbSettings() {
    Map<String, String> settings = new HashMap<>();
    settings.put("connection.driver_class", "com.mysql.cj.jdbc.Driver");
    settings.put("dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
    settings.put("hibernate.connection.username", "root");
    settings.put("hibernate.connection.password", "amAnda1092");
    //settings.put("hibernate.current_session_context_class", "thread");
    settings.put("hibernate.show_sql", "true");
    //settings.put("hibernate.format_sql", "true");
    return settings;
  }

  @Bean
  public DataSource getDataSource() {
    return DataSourceBuilder.create()
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .url("jdbc:mysql://localhost:3306/appdb?useSSL=false")
            .username("root")
            .password("amAnda1092")
            .build();
  }
}
