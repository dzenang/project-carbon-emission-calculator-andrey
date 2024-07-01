package org.example.enums;

public enum EmissionGoalCRUDQueries {
  CREATE("INSERT INTO emission_goals (goal_id, user_id, target_emission, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?)"),
  READ_ALL("SELECT * FROM emission_goals"),
  READ_BY_ID("SELECT * FROM emission_goals WHERE goal_id = ?"),
  UPDATE("UPDATE emission_goals SET target_emission = ?, start_date = ?, end_date = ?, status = ? WHERE goal_id = ?"),
  DELETE("DELETE FROM emission_goals WHERE goal_id = ?");

  private final String query;

  EmissionGoalCRUDQueries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return this.query;
  }
}
