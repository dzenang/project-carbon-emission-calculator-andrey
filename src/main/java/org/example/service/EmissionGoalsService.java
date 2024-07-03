package org.example.service;

import java.sql.SQLException;
import java.util.List;
import org.example.dao.EmissionGoalDAOImpl;
import org.example.model.EmissionGoal;

public class EmissionGoalsService {

  EmissionGoalDAOImpl emissionGoalDAO;

  public EmissionGoalsService(){
    emissionGoalDAO = new EmissionGoalDAOImpl();
  }

  public void insertEmissionGoal(EmissionGoal emissionGoal) {
    try {
      emissionGoalDAO.insert(emissionGoal);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteEmissionGoal(int id) {
    try {
      emissionGoalDAO.delete(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateEmissionGoal(EmissionGoal emissionGoal) {
    try {
      emissionGoalDAO.update(emissionGoal);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public EmissionGoal getEmissionGoal(int id) {
    try {
      return emissionGoalDAO.get(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<EmissionGoal> getEmissionGoal() {
    try {
      return emissionGoalDAO.getAll();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
