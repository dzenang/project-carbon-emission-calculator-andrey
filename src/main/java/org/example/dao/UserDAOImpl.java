package org.example.dao;

import java.sql.SQLException;
import java.util.List;
import org.example.model.User;

public class UserDAOImpl implements UserDAO {

  @Override
  public User get(long id) throws SQLException {
    return null;
  }

  @Override
  public List<User> getAll() throws SQLException {
    return List.of();
  }

  @Override
  public int insert(User user) throws SQLException {
    return 0;
  }

  @Override
  public int update(User user) throws SQLException {
    return 0;
  }

  @Override
  public int delete(long id) throws SQLException {
    return 0;
  }
}
