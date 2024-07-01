package org.example.dao;

import java.sql.SQLException;
import java.util.List;
import org.example.model.EmissionGoal;

public class EmissionGoalDAOImpl implements EmissionGoalDAO{

  @Override
  public EmissionGoal get(long id) throws SQLException {
    return null;
  }

  @Override
  public List<EmissionGoal> getAll() throws SQLException {
    return List.of();
  }

  @Override
  public int insert(EmissionGoal emissionGoal) throws SQLException {
    return 0;
  }

  @Override
  public int update(EmissionGoal emissionGoal) throws SQLException {
    return 0;
  }

  @Override
  public int delete(long id) throws SQLException {
    return 0;
  }
}
