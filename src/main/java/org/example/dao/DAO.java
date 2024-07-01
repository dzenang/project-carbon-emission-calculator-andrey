package org.example.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

  T get(long id) throws SQLException;
  List<T> getAll() throws SQLException;
  int insert(T t) throws SQLException;
  int update(T t) throws SQLException;
  int delete(long id) throws SQLException;
}
