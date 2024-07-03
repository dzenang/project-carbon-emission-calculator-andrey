package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
import org.example.config.ConnectionFactory;
import org.example.enums.OperationQueries;
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
    private static Connection connection;

    public static void main( String[] args ) throws SQLException {
        connection = ConnectionFactory.getConnection();
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
        System.out.println("Sorry, this functionality is not implemented yet.");
    }

    private static void readEntryFromTable(String tableName) {
        switch (tableName) {
            case "activity":
                System.out.println("Sorry, this functionality is not implemented yet.");
                break;
            case "emission_factors":
                System.out.println("Sorry, this functionality is not implemented yet.");
                break;
            case "emission_goals":
                System.out.println("Sorry, this functionality is not implemented yet.");
                break;
            case "user_emissions":
                System.out.println("Sorry, this functionality is not implemented yet.");
                break;
            case "users":
                readUsersTable();
                break;
            default: break;
        }
    }

    private static void readUsersTable() {
        System.out.println("Do you want to read data for all existed users(1) or only for one(2)? Select 3 to return to previous menu");
        int operationChoice = scanner.nextInt();
        scanner.nextLine();

        switch (operationChoice) {
            case 1:
                new UserService().getAllUsers().forEach(System.out::println);
                break;
            case 2:
                System.out.println("Please enter user id:");
                int userId = scanner.nextInt();
                scanner.nextLine();
                System.out.println(new UserService().getUser(userId));
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void updateEntryInTable(String tableName) {
        //todo
        System.out.println("Sorry, this functionality is not implemented yet.");
    }

    private static void insertEntryInTable(String tableName) throws SQLException {
        switch (tableName) {
            case "activity":
                insertIntoActivityTable();
                break;
            case "emission_factors":
                insertIntoEmissionFactorsTable();
                break;
            case "emission_goals":
                insertIntoEmissionGoalsTable();
                break;
            case "user_emissions":
                insertIntoUserEmissionsTable();
                break;
            case "users":
                insertIntoUsersTable();
                break;
            default: break;
        }
    }

    private static void insertIntoUsersTable() {
        System.out.println("Enter user id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Description:");
        String email = scanner.nextLine();
        new UserService().createUser(new User(id, name, email));
        System.out.println("Inserted user successfully.");
    }

    private static void insertIntoUserEmissionsTable() {
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
    }

    private static void insertIntoEmissionGoalsTable() {
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
    }

    private static void insertIntoEmissionFactorsTable() {
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
    }

    private static void insertIntoActivityTable() {
        System.out.println("Enter activity id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Description:");
        String description = scanner.nextLine();
        new ActivityService().insertActivity(new Activity(id, name, description));
        System.out.println("Inserted activity successfully.");
    }

    private static void handleOtherSQLOperation() throws SQLException {
        while (true) {
            System.out.println("Select operation for table1: 1. get monthly emission per user, 2. get total emission for user, 0. return to previous menu");
            int operationChoice = scanner.nextInt();
            scanner.nextLine();
            String query;

            switch (operationChoice) {
                case 1:
                    query = OperationQueries.MONTLY_EMISSIONS.getQuery();
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        ResultSet resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {
                            System.out.printf("Total emission for month number %s is %f\n", resultSet.getString("month"), resultSet.getDouble("emission per month"));
                        }
                    }
                    break;
                case 2:
                    query = OperationQueries.TOTAL_EMISSIONS.getQuery();
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        System.out.println("Please enter name of the user:");
                        String userName = scanner.nextLine();
                        preparedStatement.setString(1, userName);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            System.out.println(resultSet.getString("username"));
                            System.out.println(resultSet.getInt("sum"));
                        }
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
