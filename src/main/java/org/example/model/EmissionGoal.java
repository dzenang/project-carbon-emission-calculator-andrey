package org.example.model;

import java.time.LocalDate;

public class EmissionGoal {

  long goalId;
  long userId;
  double targetEmission;
  LocalDate startDate;
  LocalDate endDate;
  String status;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public double getTargetEmission() {
    return targetEmission;
  }

  public void setTargetEmission(double targetEmission) {
    this.targetEmission = targetEmission;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getGoalId() {
    return goalId;
  }

  public void setGoalId(long goalId) {
    this.goalId = goalId;
  }

  @Override
  public String toString() {
    return "EmissionGoal{" +
        "goalId=" + goalId +
        ", userId=" + userId +
        ", targetEmission=" + targetEmission +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", status='" + status + '\'' +
        '}';
  }

  public EmissionGoal(long goalId, long userId, double targetEmission, LocalDate startDate,
      LocalDate endDate, String status) {
    this.goalId = goalId;
    this.userId = userId;
    this.targetEmission = targetEmission;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
  }
}
