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
    userDAO.insert(user);
  }

  public void deleteUser(int id) {
    userDAO.delete(id);
  }

  public void updateUser(User user) {
    userDAO.update(user);
  }

  public User getUser(int id) {
    return userDAO.get(id);
  }

  public List<User> getAllUsers() {
    return userDAO.getAll();
  }
}
