package org.example.enums;

public enum OperationQueries {
  //total emissions for a user
  //Comparing emissions between different activities.
  //Listing activities with emissions above a certain threshold.

  TOTAL_EMISSIONS("SELECT sum(ue.emission), u.username FROM user_emissions ue JOIN users u ON u.user_id = ue.user_id GROUP BY u.username HAVING u.username = ?"),
  EMISSIONS_PER_ACTIVITIES("SELECT a.name, avg(ue.emission) FROM user_emissions ue JOIN activities a ON ue.activity_id = a.activity_id GROUP BY a.name ORDER BY avg(ue.emission)");

  private final String query;

  OperationQueries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return this.query;
  }
}
