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
    userEmissionDAO.insert(userEmission);
  }

  public void deleteUserEmission(int id) {
    userEmissionDAO.delete(id);
  }

  public void updateUserEmission(UserEmission userEmission) {
    userEmissionDAO.update(userEmission);
  }

  public UserEmission getUserEmission(int id) {
    return userEmissionDAO.get(id);
  }

  public List<UserEmission> getUserEmission() {
    return userEmissionDAO.getAll();
  }
}
