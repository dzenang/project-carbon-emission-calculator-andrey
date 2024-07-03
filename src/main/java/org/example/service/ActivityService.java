package org.example.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import org.example.config.ConnectionFactory;
import org.example.dao.ActivityDAOImpl;
import org.example.model.Activity;

public class ActivityService {

  ActivityDAOImpl activityDAO;

  public ActivityService() {
    activityDAO = new ActivityDAOImpl();
  }

  public void insertActivity(Activity activity) {
    try {
      activityDAO.insert(activity);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteActivity(int id) {
    try {
      activityDAO.delete(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateActivity(Activity activity) {
    try {
      activityDAO.update(activity);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Activity getActivity(int id) {
    try {
      return activityDAO.get(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Activity> getActivities() {
    try {
      return activityDAO.getAll();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
