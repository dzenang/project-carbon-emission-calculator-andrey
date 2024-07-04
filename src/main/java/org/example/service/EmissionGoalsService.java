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
    emissionGoalDAO.insert(emissionGoal);
  }

  public void deleteEmissionGoal(int id) {
    emissionGoalDAO.delete(id);
  }

  public void updateEmissionGoal(EmissionGoal emissionGoal) {
    emissionGoalDAO.update(emissionGoal);
  }

  public EmissionGoal getEmissionGoal(int id) {
    return emissionGoalDAO.get(id);
  }

  public List<EmissionGoal> getEmissionGoal() {
    return emissionGoalDAO.getAll();
  }
}
