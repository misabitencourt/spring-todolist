package com.misabitencourt.web.todolist.todolistreactive.repository;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
public class DatabaseConfig extends AbstractR2dbcConfiguration {

  @Override
  @Bean
  public ConnectionFactory connectionFactory() {
    return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
        .host("localhost")
        .port(5432)
        .username("postgres")
        .password("password")
        .database("todolist")
        .build());
  }

}
