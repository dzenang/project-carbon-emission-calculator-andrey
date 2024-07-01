package org.example.dao;

import java.sql.SQLException;
import java.util.List;
import org.example.model.EmissionFactor;

public class EmissionFactorDAOImpl implements EmissionFactorDAO {

  @Override
  public EmissionFactor get(long id) throws SQLException {
    return null;
  }

  @Override
  public List<EmissionFactor> getAll() throws SQLException {
    return List.of();
  }

  @Override
  public int insert(EmissionFactor emissionFactor) throws SQLException {
    return 0;
  }

  @Override
  public int update(EmissionFactor emissionFactor) throws SQLException {
    return 0;
  }

  @Override
  public int delete(long id) throws SQLException {
    return 0;
  }
}
