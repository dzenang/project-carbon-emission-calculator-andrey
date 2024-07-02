package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.config.ConnectionFactory;
import org.example.dao.ActivityDAOImpl;
import org.example.dao.EmissionFactorDAOImpl;
import org.example.dao.EmissionGoalDAO;
import org.example.dao.EmissionGoalDAOImpl;
import org.example.dao.UserDAO;
import org.example.dao.UserDAOImpl;
import org.example.dao.UserEmissionDAO;
import org.example.dao.UserEmissionDAOImpl;
import org.example.enums.Status;
import org.example.model.Activity;
import org.example.model.EmissionFactor;
import org.example.model.EmissionGoal;
import org.example.model.User;
import org.example.model.UserEmission;

public class ClassesTests extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public ClassesTests( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( ClassesTests.class );
  }

  /**
   * Rigourous Test :-)
   */
  public void testApp() throws SQLException {
    assertTrue( true );
  }

  public void testActivity() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    int randomId = new Random().nextInt();
    Activity activity = new Activity(randomId, "testActivityName", "This is a activity");
    ActivityDAOImpl activityDAO = new ActivityDAOImpl();

    int totalNumberOfEntries = activityDAO.getAll().size();
    assertEquals("Inserted wrong number of activity", 1, activityDAO.insert(activity));
    assertEquals("Wrong name of inserted activity", "testActivityName", activityDAO.get(randomId).getName());
    assertEquals("Deleted wrong number of activity", 1, activityDAO.delete(randomId));
    assertEquals("Wrong totalNumberOfEntries", totalNumberOfEntries, activityDAO.getAll().size());
  }

  public void testEmissionFactor() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    int randomId = new Random().nextInt();
    EmissionFactor emissionFactor = new EmissionFactor(randomId, 1, 2.,"unitbla");
    EmissionFactorDAOImpl emissionFactorDAO = new EmissionFactorDAOImpl();

    int totalNumberOfEntries = emissionFactorDAO.getAll().size();
    assertEquals("Inserted wrong emission factor", 1, emissionFactorDAO.insert(emissionFactor));
    assertEquals("Deleted wrong number of activity", 1, emissionFactorDAO.delete(randomId));
    assertEquals("Wrong totalNumberOfEntries", totalNumberOfEntries, emissionFactorDAO.getAll().size());
  }

  public void testEmissionGoal() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    int randomId = new Random().nextInt();
    EmissionGoal emissionGoal = new EmissionGoal(randomId, 1, 2., LocalDate.now(), LocalDate.now().plusDays(1), Status.EXCEED);
    EmissionGoalDAO emissionGoalDAO = new EmissionGoalDAOImpl();

    int totalNumberOfEntries = emissionGoalDAO.getAll().size();
    assertEquals("Inserted wrong emission factor", 1, emissionGoalDAO.insert(emissionGoal));
    assertEquals("Deleted wrong number of activity", 1, emissionGoalDAO.delete(randomId));
    assertEquals("Wrong totalNumberOfEntries", totalNumberOfEntries, emissionGoalDAO.getAll().size());
  }

  public void testUsers() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    int randomId = new Random().nextInt();
    User user = new User(randomId, "TestUser", "test@test.com");
    UserDAOImpl userDAO = new UserDAOImpl();

    int totalNumberOfEntries = userDAO.getAll().size();
    assertEquals("Inserted wrong emission factor", 1, userDAO.insert(user));
    assertEquals("Deleted wrong number of activity", 1, userDAO.delete(randomId));
    assertEquals("Wrong totalNumberOfEntries", totalNumberOfEntries, userDAO.getAll().size());
  }

  public void testUserEmission() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    int randomId = new Random().nextInt();
    UserEmission userEmission = new UserEmission(randomId, 1, 1, 2., 3., LocalDate.now());
    UserEmissionDAOImpl userEmissionDAO = new UserEmissionDAOImpl();

    int totalNumberOfEntries = userEmissionDAO.getAll().size();
    assertEquals("Inserted wrong emission factor", 1, userEmissionDAO.insert(userEmission));
    assertEquals("Deleted wrong number of activity", 1, userEmissionDAO.delete(randomId));
    assertEquals("Wrong totalNumberOfEntries", totalNumberOfEntries, userEmissionDAO.getAll().size());
  }

  public void testUserEmissionBkl() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    for (int i = 0; i < 5; i++) {
      int randomId = new Random().nextInt();
      double randomDouble = Math.random();
      UserEmission userEmission = new UserEmission(randomId, 1, 2, 2., randomDouble, LocalDate.now());
      UserEmissionDAOImpl userEmissionDAO = new UserEmissionDAOImpl();

      int totalNumberOfEntries = userEmissionDAO.getAll().size();
      assertEquals("Inserted wrong emission factor", 1, userEmissionDAO.insert(userEmission));
    }
    //assertEquals("Deleted wrong number of activity", 1, userEmissionDAO.delete(randomId));
    //assertEquals("Wrong totalNumberOfEntries", totalNumberOfEntries, userEmissionDAO.getAll().size());
  }
}
