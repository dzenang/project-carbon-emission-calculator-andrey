package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import org.example.config.ConnectionFactory;
import org.example.dao.ActivityDAOImpl;
import org.example.model.Activity;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        if ( connection != null ) {
            System.out.println("DB connection established");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of activity");
        String activityName = scanner.next();

        Activity activity = new Activity(2, activityName, "This is a activity");
        ActivityDAOImpl activityDAO = new ActivityDAOImpl();
        System.out.printf("Inserted %d entries\n", activityDAO.insert(activity));

        System.out.println("This is activity for id = 1: " + activityDAO.get(1).toString());
        System.out.println("This is all activities from the table: " + activityDAO.getAll().toString());

    }
}
