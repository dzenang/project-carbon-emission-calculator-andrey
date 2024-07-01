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
  public Activity get(long id) throws SQLException {
    String query = ActivityCRUDQueries.READ_BY_ID.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        return mapResultSetToActivity(resultSet);
      }
    }
    return null;
  }

  @Override
  public List<Activity> getAll() throws SQLException {
    String query = ActivityCRUDQueries.READ_ALL.getQuery();
    List<Activity> activityList = new ArrayList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        activityList.add(mapResultSetToActivity(resultSet));
      }
    }
    return activityList;
  }

  @Override
  public int insert(Activity activity) throws SQLException {
    String query = ActivityCRUDQueries.CREATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, activity.getActivityId());
      preparedStatement.setString(2, activity.getName());
      preparedStatement.setString(3, activity.getDescription());
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public int update(Activity activity) throws SQLException {
    String query = ActivityCRUDQueries.UPDATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, activity.getName());
      preparedStatement.setString(2, activity.getDescription());
      preparedStatement.setLong(3, activity.getActivityId());
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public int delete(long id) throws SQLException {
    String query = ActivityCRUDQueries.DELETE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      return preparedStatement.executeUpdate();
    }
  }

  private Activity mapResultSetToActivity(ResultSet resultSet) throws SQLException {
    return new Activity(
        resultSet.getLong("activity_id"),
        resultSet.getString("name"),
        resultSet.getString("description"));
  }
}
