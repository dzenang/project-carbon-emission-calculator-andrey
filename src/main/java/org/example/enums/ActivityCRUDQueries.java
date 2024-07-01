package org.example.enums;

public enum ActivityCRUDQueries {
  CREATE("INSERT INTO activities (activity_id, name, description) VALUES (?, ?, ?)"),
  READ_ALL("SELECT * FROM activities"),
  READ_BY_ID("SELECT * FROM activities WHERE activity_id = ?"),
  UPDATE("UPDATE activities SET name = ?, description = ? WHERE activity_id = ?"),
  DELETE("DELETE FROM activities WHERE activity_id = ?");

  private final String query;

  ActivityCRUDQueries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return this.query;
  }
}
