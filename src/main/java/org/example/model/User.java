package org.example.model;

public class User {

  long userId;
  String userName;
  String email;
  String hash;
  String salt;

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "User{" +
        "userId=" + userId +
        ", userName='" + userName + '\'' +
        ", email='" + email + '\'' +
        '}';
  }

  public User(long userId, String userName, String email) {
    this.userId = userId;
    this.userName = userName;
    this.email = email;
  }

  public User(long userId, String userName, String email, String hash, String salt) {
    this.userId = userId;
    this.userName = userName;
    this.email = email;
    this.hash = hash;
    this.salt = salt;
  }
}
