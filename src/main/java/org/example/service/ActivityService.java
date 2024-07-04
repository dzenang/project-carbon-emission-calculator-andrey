package org.example.service;

import java.util.List;
import org.example.dao.ActivityDAOImpl;
import org.example.model.Activity;

public class ActivityService {

  ActivityDAOImpl activityDAO;

  public ActivityService() {
    activityDAO = new ActivityDAOImpl();
  }

  public void insertActivity(Activity activity) {
    activityDAO.insert(activity);
  }

  public void deleteActivity(int id) {
    activityDAO.delete(id);
  }

  public void updateActivity(Activity activity) {
    activityDAO.update(activity);
  }

  public Activity getActivity(int id) {
    return activityDAO.get(id);
  }

  public List<Activity> getActivities() {
    return activityDAO.getAll();
  }
}
