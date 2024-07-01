package org.example.enums;

public enum UserEmissionCRUDQueries {
  CREATE("INSERT INTO user_emissions (emission_id, user_id, activity_id, quantity, emission, date) VALUES (?, ?, ?, ?, ?, ?)"),
  READ_ALL("SELECT * FROM user_emissions"),
  READ_BY_ID("SELECT * FROM user_emissions WHERE emission_id = ?"),
  UPDATE("UPDATE user_emissions SET quantity = ?, emission = ?, date = ? WHERE emission_id = ?"),
  DELETE("DELETE FROM user_emissions WHERE emission_id = ?");

  private final String query;

  UserEmissionCRUDQueries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return this.query;
  }
}
