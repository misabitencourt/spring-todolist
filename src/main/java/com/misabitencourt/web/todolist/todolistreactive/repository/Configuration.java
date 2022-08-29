package com.misabitencourt.web.todolist.todolistreactive.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
public class Configuration {

  @Value("databaseurl")
  private String databaseurl;

  @Value("databasename")
  private String databasename;

  @Value("databaseuser")
  private String databaseuser;

  @Value("databaseport")
  private String databaseport;

  @Value("databasepassword")
  private String databasepassword;

}