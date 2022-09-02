
# Stack: Postgres + SpringWebflux + Angular + TailwindCSS

It is a P.O.C. (Proof of Concept) of a web application stack. 
The program itself is just a todo list.

 - The postgres SGBD database is used to persist data; 
 - Java with Spring webflux is used to create a JSON webservice backend;
 - Angular is used to create a User Interface client;

![TodoList](https://github.com/misabitencourt/spring-todolist/raw/master/docs/todolist.gif)

# Requirements

 - JDK 11+ with Gradle;
 - Postgres SGDB server (local or remote);
 - NodeJS 14+

# Backend configuration

 - Locate the ```application.properties``` configuration file and configure your Postgres Database server configuration;
 - Locate the SQL file ```dml.sql``` and run it on your Postgres Database;
 - Use Gradle to install the Java project dependencies (via command line or an IDE);

# Frontend configuration

 - On the directory ```angular-web-client```, run the commands: ```npm install``` and ```npm start```

