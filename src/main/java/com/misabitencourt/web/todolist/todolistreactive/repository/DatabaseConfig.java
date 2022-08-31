package com.misabitencourt.web.todolist.todolistreactive.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
public class DatabaseConfig extends AbstractR2dbcConfiguration {

  @Value("${database.url}")
  private String databaseUrl;

  @Value("${database.port}")
  private String databasePort;

  @Value("${database.username}")
  private String databaseUsername;

  @Value("${database.password}")
  private String databasePassword;

  @Value("${database.name}")
  private String databaseName;

  @Override
  @Bean
  public ConnectionFactory connectionFactory() {
    return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
        .host(this.databaseUrl)
        .port(Integer.valueOf(this.databasePort))
        .username(this.databaseUsername)
        .password(this.databasePassword)
        .database(this.databaseName)
        .build());
  }

}
