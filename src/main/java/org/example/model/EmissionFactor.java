package org.example.model;

public class EmissionFactor {

  long factorId;

  @Override
  public String toString() {
    return "EmissionFactor{" +
        "factorId=" + factorId +
        ", activityId=" + activityId +
        ", factor=" + factor +
        ", unit='" + unit + '\'' +
        '}';
  }

  long activityId;
  double factor;
  String unit;


  public long getFactorId() {
    return factorId;
  }

  public void setFactorId(long factorId) {
    this.factorId = factorId;
  }

  public long getActivityId() {
    return activityId;
  }

  public void setActivityId(long activityId) {
    this.activityId = activityId;
  }

  public double getFactor() {
    return factor;
  }

  public void setFactor(double factor) {
    this.factor = factor;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public EmissionFactor(long factorId, long activityId, double factor, String unit) {
    this.factorId = factorId;
    this.activityId = activityId;
    this.factor = factor;
    this.unit = unit;
  }
}
