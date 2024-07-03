package org.example.service;

import java.sql.SQLException;
import java.util.List;
import org.example.dao.UserDAOImpl;
import org.example.model.User;

public class UserService {

  UserDAOImpl userDAO;

  public UserService() {
    userDAO = new UserDAOImpl();
  }

  public void createUser(User user) {
    try {
      userDAO.insert(user);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteUser(int id) {
    try {
      userDAO.delete(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateUser(User user) {
    try {
      userDAO.update(user);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public User getUser(int id) {
    try {
      return userDAO.get(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<User> getAllUsers() {
    try {
      return userDAO.getAll();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
