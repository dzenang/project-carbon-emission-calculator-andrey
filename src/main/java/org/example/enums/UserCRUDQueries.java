package org.example.enums;

public enum UserCRUDQueries {
  CREATE("INSERT INTO users (user_id, username, email, password_hash, salt) VALUES (?, ?, ?, ?, ?)"),
  READ_ALL("SELECT * FROM users"),
  READ_BY_ID("SELECT * FROM users WHERE user_id = ?"),
  UPDATE("UPDATE users SET username = ?, email = ?, password_hash = ?, salt = ? WHERE user_id = ?"),
  DELETE("DELETE FROM users WHERE user_id = ?"),
  READ_BY_NAME("SELECT * FROM users WHERE username = ?"),;

  private final String query;

  UserCRUDQueries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return this.query;
  }
}
