package org.example.enums;

public enum OperationQueries {
  //total emissions for a user
  //Comparing emissions between different activities.
  //Listing activities with emissions above a certain threshold.

  TOTAL_EMISSIONS("SELECT sum(ue.emission) as \"sum\", u.username as \"username\" FROM user_emissions ue JOIN users u ON u.user_id = ue.user_id GROUP BY u.username HAVING u.username = ?"),
  EMISSIONS_PER_ACTIVITIES("SELECT a.name as \"name\", avg(ue.emission) as \"average emission\" FROM user_emissions ue JOIN activities a ON ue.activity_id = a.activity_id GROUP BY a.name ORDER BY avg(ue.emission)"),
  ACTIVITIES_ABOVE_EMISSION_THRESHOLD("SELECT ue.emission as \"emission\", a.name as \"name\" FROM user_emissions ue JOIN activities a ON ue.activity_id = a.activity_id WHERE ue.emission > ?"),
  MONTLY_EMISSIONS("SELECT to_char(date, 'MM') AS MONTH, SUM(emission) AS \"emission per month\" FROM user_emissions GROUP BY to_char(date, 'MM') ORDER BY SUM(emission)"),
  EXCEEDED_EMISSION_GOALS("SELECT u.username AS \"USERNAME\" FROM users u\n"
      + "JOIN emission_goals eg ON eg.user_id = u.user_id\n"
      + "WHERE eg.target_emission < (SELECT SUM(ue.emission) AS \"total emission for period\"\n"
      + "FROM users u\n"
      + "JOIN user_emissions ue ON u.user_id = ue.user_id\n"
      + "JOIN emission_goals eg ON eg.user_id = u.user_id\n"
      + "WHERE ue.date > eg.start_date AND ue.date < eg.end_date);");



  private final String query;

  OperationQueries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return this.query;
  }
}
