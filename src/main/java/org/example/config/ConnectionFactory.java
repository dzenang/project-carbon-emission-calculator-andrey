package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  private static final String URL = "jdbc:postgresql://localhost:5432/carbon_emission";
  private static final String USER = "postgres";
  private static final String PASSWORD = "111111";
  private static final String POSTGRES_DRIVER = "org.postgresql.Driver";

  private ConnectionFactory(){
  }

  public static Connection getConnection() throws SQLException {
    try {
      Class.forName(POSTGRES_DRIVER);
      Connection connection = null;
      connection = DriverManager.getConnection(URL, USER, PASSWORD);
      return connection;
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
