package org.example.service;

import java.sql.SQLException;
import java.util.List;
import org.example.dao.EmissionFactorDAOImpl;
import org.example.dao.UserEmissionDAOImpl;
import org.example.model.EmissionFactor;
import org.example.model.UserEmission;

public class UserEmissionService {

  UserEmissionDAOImpl userEmissionDAO;

  public UserEmissionService(){
    userEmissionDAO = new UserEmissionDAOImpl();
  }

  public void insertUserEmission(UserEmission userEmission) {
    try {
      userEmissionDAO.insert(userEmission);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteUserEmission(int id) {
    try {
      userEmissionDAO.delete(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateUserEmission(UserEmission userEmission) {
    try {
      userEmissionDAO.update(userEmission);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public UserEmission getUserEmission(int id) {
    try {
      return userEmissionDAO.get(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<UserEmission> getUserEmission() {
    try {
      return userEmissionDAO.getAll();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
