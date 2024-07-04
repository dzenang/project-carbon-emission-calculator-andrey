package org.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.config.ConnectionFactory;
import org.example.enums.EmissionGoalCRUDQueries;
import org.example.enums.UserCRUDQueries;
import org.example.enums.UserEmissionCRUDQueries;
import org.example.model.EmissionGoal;
import org.example.model.User;
import org.example.model.UserEmission;

public class UserEmissionDAOImpl implements UserEmissionDAO {

  private Connection connection;

  public UserEmissionDAOImpl(){
    try {
      this.connection = ConnectionFactory.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public UserEmission get(long id) {
    String query = UserEmissionCRUDQueries.READ_BY_ID.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        return mapResultSetToUserEmission(resultSet);
      }
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return null;
  }

  @Override
  public List<UserEmission> getAll() {
    String query = UserEmissionCRUDQueries.READ_ALL.getQuery();
    List<UserEmission> userEmissionList = new ArrayList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        userEmissionList.add(mapResultSetToUserEmission(resultSet));
      }
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return userEmissionList;
  }

  @Override
  public int insert(UserEmission userEmission) {
    String query = UserEmissionCRUDQueries.CREATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, userEmission.getEmissionId());
      preparedStatement.setLong(2, userEmission.getUserId());
      preparedStatement.setDouble(3, userEmission.getActivityId());
      preparedStatement.setDouble(4, userEmission.getQuantity());
      preparedStatement.setDouble(5, userEmission.getEmission());
      preparedStatement.setDate(6, Date.valueOf(userEmission.getDate()));
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return 0;
  }

  @Override
  public int update(UserEmission userEmission) {
    String query = UserEmissionCRUDQueries.UPDATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setDouble(1, userEmission.getQuantity());
      preparedStatement.setDouble(2, userEmission.getEmission());
      preparedStatement.setDate(3, Date.valueOf(userEmission.getDate()));
      preparedStatement.setLong(4, userEmission.getEmissionId());
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return 0;
  }

  @Override
  public int delete(long id) {
    String query = UserEmissionCRUDQueries.DELETE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return 0;
  }

  private UserEmission mapResultSetToUserEmission(ResultSet resultSet) {
    try {
      return new UserEmission(
          resultSet.getLong("emission_id"),
          resultSet.getLong("user_id"),
          resultSet.getLong("activity_id"),
          resultSet.getDouble("quantity"),
          resultSet.getDouble("emission"),
          resultSet.getDate("date").toLocalDate()
      );
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return null;
  }
}
