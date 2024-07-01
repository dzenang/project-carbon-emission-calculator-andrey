package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.config.ConnectionFactory;
import org.example.enums.ActivityCRUDQueries;
import org.example.enums.EmissionFactorCRUDQueries;
import org.example.enums.EmissionGoalCRUDQueries;
import org.example.model.Activity;
import org.example.model.EmissionFactor;

public class EmissionFactorDAOImpl implements EmissionFactorDAO {

  private Connection connection;

  public EmissionFactorDAOImpl(){
    try {
      this.connection = ConnectionFactory.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public EmissionFactor get(long id) throws SQLException {
    String query = EmissionFactorCRUDQueries.READ_BY_ID.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        return mapResultSetToEmissionFactor(resultSet);
      }
    }
    return null;
  }

  @Override
  public List<EmissionFactor> getAll() throws SQLException {
    String query = EmissionFactorCRUDQueries.READ_ALL.getQuery();
    List<EmissionFactor> emissionFactorList = new ArrayList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        emissionFactorList.add(mapResultSetToEmissionFactor(resultSet));
      }
    }
    return emissionFactorList;
  }

  @Override
  public int insert(EmissionFactor emissionFactor) throws SQLException {
    String query = EmissionFactorCRUDQueries.CREATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, emissionFactor.getFactorId());
      preparedStatement.setLong(2, emissionFactor.getActivityId());
      preparedStatement.setDouble(3, emissionFactor.getFactor());
      preparedStatement.setString(4, emissionFactor.getUnit());
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public int update(EmissionFactor emissionFactor) throws SQLException {
    String query = EmissionFactorCRUDQueries.UPDATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setDouble(1, emissionFactor.getFactor());
      preparedStatement.setString(2, emissionFactor.getUnit());
      preparedStatement.setLong(3, emissionFactor.getFactorId());
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public int delete(long id) throws SQLException {
    String query = EmissionFactorCRUDQueries.DELETE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      return preparedStatement.executeUpdate();
    }
  }

  private EmissionFactor mapResultSetToEmissionFactor(ResultSet resultSet) throws SQLException {
    return new EmissionFactor(
        resultSet.getLong("factor_id"),
        resultSet.getLong("activity_id"),
        resultSet.getDouble("factor"),
        resultSet.getString("unit"));
  }
}
