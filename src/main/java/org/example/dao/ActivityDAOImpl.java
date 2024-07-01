package org.example.dao;

import java.sql.SQLException;
import java.util.List;
import org.example.model.Activity;

public class ActivityDAOImpl implements ActivityDAO {

  @Override
  public Activity get(long id) throws SQLException {
    return null;
  }

  @Override
  public List<Activity> getAll() throws SQLException {
    return List.of();
  }

  @Override
  public int insert(Activity activity) throws SQLException {
    return 0;
  }

  @Override
  public int update(Activity activity) throws SQLException {
    return 0;
  }

  @Override
  public int delete(long id) throws SQLException {
    return 0;
  }
}
