package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.sound.midi.Soundbank;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.config.ConnectionFactory;
import org.example.dao.ActivityDAOImpl;
import org.example.enums.OperationQueries;
import org.example.enums.Status;
import org.example.model.Activity;
import org.example.service.StatusUtil;

public class SQLOperationsTests extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public SQLOperationsTests( String testName )
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

  public void testTotalEmissionsForUser() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    String query = OperationQueries.TOTAL_EMISSIONS.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, "TestUser");
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        System.out.println(resultSet.getString("username"));
        System.out.println(resultSet.getInt("sum"));
      }
    }
  }

  public void testEmissionsPerActivities() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    String query = OperationQueries.EMISSIONS_PER_ACTIVITIES.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        System.out.println(resultSet.getString("name"));
        System.out.println(resultSet.getInt("average emission"));
      }
    }
  }

  public void testActivitiesWithEmissionsAboveThreshold() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    String query = OperationQueries.ACTIVITIES_ABOVE_EMISSION_THRESHOLD.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1,2);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        System.out.println(resultSet.getString("emission"));
        System.out.println(resultSet.getString("name"));
      }
    }
  }

  public void testMonthlyEmissions() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    String query = OperationQueries.MONTLY_EMISSIONS.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        System.out.printf("Total emission for month number %s is %f\n", resultSet.getString("month"), resultSet.getDouble("emission per month"));
      }
    }
  }

  public void testExceededEmissionGoals() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    String query = OperationQueries.EXCEEDED_EMISSION_GOALS.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();

      System.out.println("Users who exceeded emission goals");
      while (resultSet.next()) {
        System.out.println(resultSet.getString("username"));
      }
    }
  }

  public void testFilterEmissionGoalsBasedOnStatus() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    String query = OperationQueries.FILTER_BASED_ON_STATUS.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, StatusUtil.toString(Status.ACHIEVED));
      ResultSet resultSet = preparedStatement.executeQuery();
      ResultSetMetaData rsmd = resultSet.getMetaData();
      int columnCount = rsmd.getColumnCount();
      while (resultSet.next()) {
        IntStream.range(1, columnCount + 1).forEach(i -> {
          try {
            System.out.print(resultSet.getString(i) + " ");
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
        });
        System.out.println();
      }
    }
  }

  public void testFilterTotalEmissionByMinimum() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    String query = OperationQueries.FILTER_TOTAL_EMISSIONS_BY_MINIMUM.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, 1);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        System.out.print(resultSet.getString("activity") + " " + resultSet.getString("total emissions"));
        System.out.println();
      }
    }
  }

  public void testTop3HighestAvgEmissions() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);

    String query = OperationQueries.TOP_3_ACTIVITIES_WITH_HIGHEST_AVR_EMISSIONS.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        System.out.print(resultSet.getString("activity") + " " + resultSet.getString("average emissions"));
        System.out.println();
      }
    }
  }
}
