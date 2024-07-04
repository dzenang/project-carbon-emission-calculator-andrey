package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
import javax.sound.midi.Soundbank;
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
        switch (tableName) {
            case "activity":
                deleteEntryFromActivity();
                break;
            case "emission_factors":
                deleteEntryFromEmissionFactors();
                break;
            case "emission_goals":
                deleteEntryFromEmissionGoals();
                break;
            case "user_emissions":
                deleteEntryFtomUserEmissions();
                break;
            case "users":
                deleteEntryFromUsers();
                break;
            default: break;
        }
    }

    private static void deleteEntryFromUsers() {
        System.out.println("Please enter Id of entry which you would like to delete:");
        int entryId = scanner.nextInt();
        scanner.nextLine();

        new UserService().deleteUser(entryId);
        System.out.println("Entry deleted");
    }

    private static void deleteEntryFtomUserEmissions() {
        System.out.println("Please enter Id of entry which you would like to delete:");
        int entryId = scanner.nextInt();
        scanner.nextLine();

        new UserEmissionService().deleteUserEmission(entryId);
        System.out.println("Entry deleted");
    }

    private static void deleteEntryFromEmissionGoals() {
        System.out.println("Please enter Id of entry which you would like to delete:");
        int entryId = scanner.nextInt();
        scanner.nextLine();

        new EmissionGoalsService().deleteEmissionGoal(entryId);
        System.out.println("Entry deleted");
    }

    private static void deleteEntryFromEmissionFactors() {
        System.out.println("Please enter Id of entry which you would like to delete:");
        int entryId = scanner.nextInt();
        scanner.nextLine();

        new EmissionFactorService().deleteEmissionFactor(entryId);
        System.out.println("Entry deleted");
    }

    private static void deleteEntryFromActivity() {
        System.out.println("Please enter Id of entry which you would like to delete:");
        int entryId = scanner.nextInt();
        scanner.nextLine();

        new ActivityService().deleteActivity(entryId);
        System.out.println("Entry deleted");
    }

    private static void readEntryFromTable(String tableName) {
        switch (tableName) {
            case "activity":
                readActivityTable();
                break;
            case "emission_factors":
                readEmissionFactorsTable();
                break;
            case "emission_goals":
                readEmissionGoalsTable();
                break;
            case "user_emissions":
                readUserEmissionTable();
                break;
            case "users":
                readUsersTable();
                break;
            default: break;
        }
    }

    private static void readUserEmissionTable() {
        System.out.println("Do you want to read data for all existed user emissions (1) or only for one(2)? Select 3 to return to previous menu");
        int operationChoice = scanner.nextInt();
        scanner.nextLine();

        switch (operationChoice) {
            case 1:
                new UserEmissionService().getUserEmission().forEach(System.out::println);
                break;
            case 2:
                System.out.println("Please enter user emission  id:");
                int userId = scanner.nextInt();
                scanner.nextLine();
                System.out.println(new UserEmissionService().getUserEmission(userId));
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void readEmissionGoalsTable() {
        System.out.println("Do you want to read data for all existed emission goals(1) or only for one(2)? Select 3 to return to previous menu");
        int operationChoice = scanner.nextInt();
        scanner.nextLine();

        switch (operationChoice) {
            case 1:
                new EmissionGoalsService().getEmissionGoal().forEach(System.out::println);
                break;
            case 2:
                System.out.println("Please enter emission goal id:");
                int userId = scanner.nextInt();
                scanner.nextLine();
                System.out.println(new EmissionGoalsService().getEmissionGoal(userId));
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void readEmissionFactorsTable() {
        System.out.println("Do you want to read data for all existed emission factors(1) or only for one(2)? Select 3 to return to previous menu");
        int operationChoice = scanner.nextInt();
        scanner.nextLine();

        switch (operationChoice) {
            case 1:
                new EmissionFactorService().getEmissionFactor().forEach(System.out::println);
                break;
            case 2:
                System.out.println("Please enter emission factor id:");
                int userId = scanner.nextInt();
                scanner.nextLine();
                System.out.println(new EmissionFactorService().getEmissionFactor(userId));
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void readActivityTable() {
        System.out.println("Do you want to read data for all existed activities(1) or only for one(2)? Select 3 to return to previous menu");
        int operationChoice = scanner.nextInt();
        scanner.nextLine();

        switch (operationChoice) {
            case 1:
                new ActivityService().getActivities().forEach(System.out::println);
                break;
            case 2:
                System.out.println("Please enter activity id:");
                int userId = scanner.nextInt();
                scanner.nextLine();
                System.out.println(new ActivityService().getActivity(userId));
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Try again.");
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
        switch (tableName) {
            case "activity":
                updateEntryInActivityTable();
                break;
            case "emission_factors":
                updateEntryInEmissionFactorsTable();
                break;
            case "emission_goals":
                updateEntryInEmissionGoalsTable();
                break;
            case "user_emissions":
                updateEntryInUserEmissionsTable();
                break;
            case "users":
                updateEntryInUsersTable();
                break;
            default: break;
        }
    }

    private static void updateEntryInUsersTable() {
        System.out.println("Enter user id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Description:");
        String email = scanner.nextLine();
        new UserService().updateUser(new User(id, name, email));
        System.out.println("Updated user successfully.");
    }

    private static void updateEntryInUserEmissionsTable() {
        System.out.println("Attention: user emission date will be updated to current date");
        System.out.println("Enter user emission id:");
        int emissionId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter user id:");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter activity id:");
        int activityId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter quantity:");
        double quantity = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter emission:");
        double emission = scanner.nextDouble();
        scanner.nextLine();
        System.out.println();
        new UserEmissionService().updateUserEmission(new UserEmission(emissionId, userId, activityId, quantity, emission, LocalDate.now()));
        System.out.println("Updated user emission successfully.");
    }

    private static void updateEntryInEmissionGoalsTable() {
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
        new EmissionGoalsService().updateEmissionGoal(new EmissionGoal(goalId, userId, targetEmission,
            LocalDate.now(), LocalDate.now().plusDays(duration), Status.PENDING));
        System.out.println("Updated emission goal successfully.");
    }

    private static void updateEntryInEmissionFactorsTable() {
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
        new EmissionFactorService().updateEmissionFactor(new EmissionFactor(factorId, activityId, factor, unit));
        System.out.println("Updated emission factor successfully.");
    }

    private static void updateEntryInActivityTable() {
        System.out.println("Enter activity id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Description:");
        String description = scanner.nextLine();
        new ActivityService().updateActivity(new Activity(id, name, description));
        System.out.println("Updated activity successfully.");
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
