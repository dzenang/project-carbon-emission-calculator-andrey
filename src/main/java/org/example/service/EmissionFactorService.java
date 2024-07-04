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
    emissionFactorDAO.insert(emissionFactor);
  }

  public void deleteEmissionFactor(int id) {
    emissionFactorDAO.delete(id);
  }

  public void updateEmissionFactor(EmissionFactor emissionFactor) {
    emissionFactorDAO.update(emissionFactor);
  }

  public EmissionFactor getEmissionFactor(int id) {
    return emissionFactorDAO.get(id);
  }

  public List<EmissionFactor> getEmissionFactor() {
    return emissionFactorDAO.getAll();
  }
}
