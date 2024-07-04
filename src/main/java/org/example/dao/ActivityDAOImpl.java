package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.config.ConnectionFactory;
import org.example.enums.ActivityCRUDQueries;
import org.example.model.Activity;

public class ActivityDAOImpl implements ActivityDAO {

  private Connection connection;

  public ActivityDAOImpl(){
    try {
      this.connection = ConnectionFactory.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Activity get(long id) {
    String query = ActivityCRUDQueries.READ_BY_ID.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        return mapResultSetToActivity(resultSet);
      }
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return null;
  }

  @Override
  public List<Activity> getAll() {
    String query = ActivityCRUDQueries.READ_ALL.getQuery();
    List<Activity> activityList = new ArrayList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        activityList.add(mapResultSetToActivity(resultSet));
      }
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return activityList;
  }

  @Override
  public int insert(Activity activity)  {
    String query = ActivityCRUDQueries.CREATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, activity.getActivityId());
      preparedStatement.setString(2, activity.getName());
      preparedStatement.setString(3, activity.getDescription());
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return 0;
  }

  @Override
  public int update(Activity activity) {
    String query = ActivityCRUDQueries.UPDATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, activity.getName());
      preparedStatement.setString(2, activity.getDescription());
      preparedStatement.setLong(3, activity.getActivityId());
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return 0;
  }

  @Override
  public int delete(long id)  {
    String query = ActivityCRUDQueries.DELETE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return 0;
  }

  private Activity mapResultSetToActivity(ResultSet resultSet) {
    try {
      return new Activity(
          resultSet.getLong("activity_id"),
          resultSet.getString("name"),
          resultSet.getString("description"));
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return null;
  }
}
