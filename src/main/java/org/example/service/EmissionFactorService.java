package org.example.service;

import java.sql.SQLException;
import java.util.List;
import org.example.dao.EmissionFactorDAOImpl;
import org.example.dao.EmissionGoalDAOImpl;
import org.example.model.EmissionFactor;
import org.example.model.EmissionGoal;

public class EmissionFactorService {

  EmissionFactorDAOImpl emissionFactorDAO;

  public EmissionFactorService(){
    emissionFactorDAO = new EmissionFactorDAOImpl();
  }

  public void insertEmissionFactor(EmissionFactor emissionFactor) {
    try {
      emissionFactorDAO.insert(emissionFactor);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteEmissionFactor(int id) {
    try {
      emissionFactorDAO.delete(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateEmissionFactor(EmissionFactor emissionFactor) {
    try {
      emissionFactorDAO.update(emissionFactor);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public EmissionFactor getEmissionFactor(int id) {
    try {
      return emissionFactorDAO.get(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<EmissionFactor> getEmissionFactor() {
    try {
      return emissionFactorDAO.getAll();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
