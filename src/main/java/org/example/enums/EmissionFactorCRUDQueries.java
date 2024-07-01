package org.example.enums;

public enum EmissionFactorCRUDQueries {
  CREATE("INSERT INTO emission_factors (factor_id, activity_id, factor, unit) VALUES (?, ?, ?, ?)"),
  READ_ALL("SELECT * FROM emission_factors"),
  READ_BY_ID("SELECT * FROM emission_factors WHERE factor_id = ?"),
  UPDATE("UPDATE emission_factors SET factor = ?, unit = ? WHERE factor_id = ?"),
  DELETE("DELETE FROM emission_factors WHERE factor_id = ?");

  private final String query;

  EmissionFactorCRUDQueries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return this.query;
  }
}
