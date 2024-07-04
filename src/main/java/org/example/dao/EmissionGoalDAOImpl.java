package org.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.config.ConnectionFactory;
import org.example.enums.EmissionFactorCRUDQueries;
import org.example.enums.EmissionGoalCRUDQueries;
import org.example.enums.Status;
import org.example.model.EmissionFactor;
import org.example.model.EmissionGoal;
import org.example.service.StatusUtil;

public class EmissionGoalDAOImpl implements EmissionGoalDAO{

  private Connection connection;

  public EmissionGoalDAOImpl(){
    try {
      this.connection = ConnectionFactory.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public EmissionGoal get(long id) {
    String query = EmissionGoalCRUDQueries.READ_BY_ID.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return mapResultSetToEmissionGoal(resultSet);
      }
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return null;
  }

  @Override
  public List<EmissionGoal> getAll() {
    String query = EmissionGoalCRUDQueries.READ_ALL.getQuery();
    List<EmissionGoal> emissionGoalList = new ArrayList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        emissionGoalList.add(mapResultSetToEmissionGoal(resultSet));
      }
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return emissionGoalList;
  }

  @Override
  public int insert(EmissionGoal emissionGoal) {
    String query = EmissionGoalCRUDQueries.CREATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, emissionGoal.getGoalId());
      preparedStatement.setLong(2, emissionGoal.getUserId());
      preparedStatement.setDouble(3, emissionGoal.getTargetEmission());
      preparedStatement.setDate(4, Date.valueOf(emissionGoal.getStartDate()));
      preparedStatement.setDate(5, Date.valueOf(emissionGoal.getEndDate()));
      preparedStatement.setString(6, StatusUtil.toString(emissionGoal.getStatus()));
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return 0;
  }

  @Override
  public int update(EmissionGoal emissionGoal) {
    String query = EmissionGoalCRUDQueries.UPDATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setDouble(1, emissionGoal.getTargetEmission());
      preparedStatement.setDate(2, Date.valueOf(emissionGoal.getStartDate()));
      preparedStatement.setDate(3, Date.valueOf(emissionGoal.getEndDate()));
      preparedStatement.setString(4, StatusUtil.toString(emissionGoal.getStatus()));
      preparedStatement.setLong(5, emissionGoal.getGoalId());
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return 0;
  }

  @Override
  public int delete(long id) {
    String query = EmissionGoalCRUDQueries.DELETE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return 0;
  }

  private EmissionGoal mapResultSetToEmissionGoal(ResultSet resultSet) {
    try {
      return new EmissionGoal(
          resultSet.getLong("goal_id"),
          resultSet.getLong("user_id"),
          resultSet.getDouble("target_emission"),
          resultSet.getDate("start_date").toLocalDate(),
          resultSet.getDate("end_date").toLocalDate(),
          StatusUtil.fromString(resultSet.getString("status"))
          );
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return null;
  }
}
