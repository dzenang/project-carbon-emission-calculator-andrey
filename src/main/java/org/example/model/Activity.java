package org.example.model;

public class Activity {

  @Override
  public String toString() {
    return "Activity{" +
        "activityId=" + activityId +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        '}';
  }

  public long getActivityId() {
    return activityId;
  }

  public void setActivityId(long activityId) {
    this.activityId = activityId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  long activityId;
  String name;
  String description;


  public Activity(long activityId, String name, String description) {
    this.activityId = activityId;
    this.name = name;
    this.description = description;
  }
}
