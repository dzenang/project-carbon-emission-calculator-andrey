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
import org.example.model.EmissionFactor;
import org.example.model.EmissionGoal;

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
  public EmissionGoal get(long id) throws SQLException {
    String query = EmissionGoalCRUDQueries.READ_BY_ID.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        return mapResultSetToEmissionGoal(resultSet);
      }
    }
    return null;
  }

  @Override
  public List<EmissionGoal> getAll() throws SQLException {
    String query = EmissionGoalCRUDQueries.READ_ALL.getQuery();
    List<EmissionGoal> emissionGoalList = new ArrayList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        emissionGoalList.add(mapResultSetToEmissionGoal(resultSet));
      }
    }
    return emissionGoalList;
  }

  @Override
  public int insert(EmissionGoal emissionGoal) throws SQLException {
    String query = EmissionGoalCRUDQueries.CREATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, emissionGoal.getGoalId());
      preparedStatement.setLong(2, emissionGoal.getUserId());
      preparedStatement.setDouble(3, emissionGoal.getTargetEmission());
      preparedStatement.setDate(4, Date.valueOf(emissionGoal.getStartDate()));
      preparedStatement.setDate(5, Date.valueOf(emissionGoal.getEndDate()));
      preparedStatement.setString(6, emissionGoal.getStatus());
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public int update(EmissionGoal emissionGoal) throws SQLException {
    String query = EmissionGoalCRUDQueries.UPDATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setDouble(1, emissionGoal.getTargetEmission());
      preparedStatement.setDate(2, Date.valueOf(emissionGoal.getStartDate()));
      preparedStatement.setDate(3, Date.valueOf(emissionGoal.getEndDate()));
      preparedStatement.setString(4, emissionGoal.getStatus());
      preparedStatement.setLong(5, emissionGoal.getGoalId());
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public int delete(long id) throws SQLException {
    String query = EmissionGoalCRUDQueries.DELETE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      return preparedStatement.executeUpdate();
    }
  }

  private EmissionGoal mapResultSetToEmissionGoal(ResultSet resultSet) throws SQLException {
    return new EmissionGoal(
        resultSet.getLong("goal_id"),
        resultSet.getLong("user_id"),
        resultSet.getDouble("target_emission"),
        resultSet.getDate("start_date").toLocalDate(),
        resultSet.getDate("end_date").toLocalDate(),
        resultSet.getString("status")
        );
  }
}
