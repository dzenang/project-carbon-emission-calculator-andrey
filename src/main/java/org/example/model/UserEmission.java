package org.example.model;

import java.time.LocalDate;

public class UserEmission {

  long emissionId;
  long userId;
  long activityId;
  double quantity;
  double emission;
  LocalDate date;

  @Override
  public String toString() {
    return "UserEmission{" +
        "emissionId=" + emissionId +
        ", userId=" + userId +
        ", activityId=" + activityId +
        ", quantity=" + quantity +
        ", emission=" + emission +
        ", date=" + date +
        '}';
  }

  public long getEmissionId() {
    return emissionId;
  }

  public void setEmissionId(long emissionId) {
    this.emissionId = emissionId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getActivityId() {
    return activityId;
  }

  public void setActivityId(long activityId) {
    this.activityId = activityId;
  }

  public double getQuantity() {
    return quantity;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

  public double getEmission() {
    return emission;
  }

  public void setEmission(double emission) {
    this.emission = emission;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public UserEmission(long emissionId, long userId, long activityId, double quantity,
      double emission,
      LocalDate date) {
    this.emissionId = emissionId;
    this.userId = userId;
    this.activityId = activityId;
    this.quantity = quantity;
    this.emission = emission;
    this.date = date;
  }


}
