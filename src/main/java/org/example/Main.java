package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import org.example.config.ConnectionFactory;
import org.example.dao.ActivityDAOImpl;
import org.example.dao.DAO;
import org.example.dao.EmissionFactorDAOImpl;
import org.example.dao.EmissionGoalDAOImpl;
import org.example.dao.UserDAOImpl;
import org.example.dao.UserEmissionDAOImpl;
import org.example.enums.Status;
import org.example.model.Activity;
import org.example.model.EmissionFactor;
import org.example.model.EmissionGoal;
import org.example.model.User;
import org.example.model.UserEmission;
import org.example.service.ActivityService;
import org.example.service.EmissionFactorService;
import org.example.service.EmissionGoalsService;
import org.example.service.UserEmissionService;
import org.example.service.UserService;

/**
 * Hello world!
 *
 */
public class Main
{
    private static Scanner scanner = new Scanner(System.in);

    public static void main( String[] args ) throws SQLException {
       Connection connection = ConnectionFactory.getConnection();
        if ( connection != null ) {
            System.out.println("DB connection established");
        }

        while (true) {
            System.out.println("Please select table: 1 - activity, 2 - emission_factors, 3 - emission_goals, 4 - user_emissions, 5 - users, 6 - other sql operations, 0 - exit");
            int tableChoise = scanner.nextInt();
            scanner.nextLine();
            switch (tableChoise) {
                case 1: handleTableOperation("activity"); break;
                case 2: handleTableOperation("emission_factors"); break;
                case 3: handleTableOperation("emission_goals"); break;
                case 4: handleTableOperation("user_emissions"); break;
                case 5: handleTableOperation("users"); break;
                case 6: handleOtherSQLOperation(); break;
                case 0: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static void handleTableOperation(String tableName) throws SQLException {
        while (true) {
            System.out.println("Select operation for table1: 1. insert, 2. update, 3. read, 4. delete, 5. return to previous menu");
            int operationChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (operationChoice) {
                case 1:
                    insertEntryInTable(tableName);
                    break;
                case 2:
                    updateEntryInTable(tableName);
                    break;
                case 3:
                    readEntryFromTable(tableName);
                    break;
                case 4:
                    deleteEntryFromTable(tableName);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

    }

    private static void deleteEntryFromTable(String tableName) throws SQLException {
        //todo
    }

    private static void readEntryFromTable(String tableName) {
        //todo
    }

    private static void updateEntryInTable(String tableName) {
        //todo
    }

    private static void insertEntryInTable(String tableName) throws SQLException {
        switch (tableName) {
            case "activity":
                System.out.println("Enter activity id:");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Name:");
                String name = scanner.nextLine();
                System.out.println("Description:");
                String description = scanner.nextLine();
                new ActivityService().insertActivity(new Activity(id, name, description));
                System.out.println("Inserted activity successfully.");
                break;
            case "emission_factors":
                System.out.println("Enter emission factor id:");
                int factorId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter emission activity id:");
                int activityId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter emission factor:");
                double factor = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter emission unit:");
                String unit = scanner.nextLine();
                new EmissionFactorService().insertEmissionFactor(new EmissionFactor(factorId, activityId, factor, unit));
                System.out.println("Inserted emission factor successfully.");
                break;
            case "emission_goals":
                System.out.println("Enter emission goals id:");
                int goalId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter user id:");
                int userId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter target emission:");
                double targetEmission = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter duration of target emission in days:");
                long duration = scanner.nextLong();
                scanner.nextLine();
                new EmissionGoalsService().insertEmissionGoal(new EmissionGoal(goalId, userId, targetEmission,
                    LocalDate.now(), LocalDate.now().plusDays(duration), Status.PENDING));
                System.out.println("Inserted emission goal successfully.");
                break;
            case "user_emissions":
                System.out.println("Enter emission id:");
                int emissionId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter user id:");
                int userId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter activity id:");
                int activityId = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                new UserEmissionService().insertUserEmission(new UserEmission(emissionId, userId, activityId, new Random().nextDouble(), new Random().nextDouble(), LocalDate.now().minusDays(new Random().nextInt()) ));
                break;
            case "users":
                System.out.println("Enter user id:");
                id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Name:");
                name = scanner.nextLine();
                System.out.println("Description:");
                String email = scanner.nextLine();
                new UserService().createUser(new User(id, name, email));
                System.out.println("Inserted user successfully.");
                break;
            default: break;
        }
    }

    private static void handleOtherSQLOperation() throws SQLException {
        //todo
    }
}
