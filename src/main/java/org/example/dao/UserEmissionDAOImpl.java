package org.example.dao;

import java.sql.SQLException;
import java.util.List;
import org.example.model.UserEmission;

public class UserEmissionDAOImpl implements UserEmissionDAO {

  @Override
  public UserEmission get(long id) throws SQLException {
    return null;
  }

  @Override
  public List<UserEmission> getAll() throws SQLException {
    return List.of();
  }

  @Override
  public int insert(UserEmission userEmission) throws SQLException {
    return 0;
  }

  @Override
  public int update(UserEmission userEmission) throws SQLException {
    return 0;
  }

  @Override
  public int delete(long id) throws SQLException {
    return 0;
  }
}
