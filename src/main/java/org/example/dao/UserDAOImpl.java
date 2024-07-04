package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.config.ConnectionFactory;
import org.example.enums.UserCRUDQueries;
import org.example.model.User;

public class UserDAOImpl implements UserDAO {

  private Connection connection;

  public UserDAOImpl(){
    try {
      this.connection = ConnectionFactory.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public User get(long id) {
    String query = UserCRUDQueries.READ_BY_ID.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        return mapResultSetToUser(resultSet);
      }
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return null;
  }

  @Override
  public List<User> getAll() {
    String query = UserCRUDQueries.READ_ALL.getQuery();
    List<User> users = new ArrayList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        users.add(mapResultSetToUser(resultSet));
      }
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return users;
  }

  @Override
  public int insert(User user) {
    String query = UserCRUDQueries.CREATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, user.getUserId());
      preparedStatement.setString(2, user.getUserName());
      preparedStatement.setString(3, user.getEmail());
      preparedStatement.setString(4, user.getHash());
      preparedStatement.setString(5, user.getSalt());
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return 0;
  }

  @Override
  public int update(User user) {
    String query = UserCRUDQueries.UPDATE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, user.getUserName());
      preparedStatement.setString(2, user.getEmail());
      preparedStatement.setString(3, user.getHash());
      preparedStatement.setString(4, user.getSalt());
      preparedStatement.setLong(5, user.getUserId());
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return 0;
  }

  @Override
  public int delete(long id) {
    String query = UserCRUDQueries.DELETE.getQuery();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setLong(1, id);
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return 0;
  }

  private User mapResultSetToUser(ResultSet resultSet) {
    try {
      return new User(
          resultSet.getLong("user_id"),
          resultSet.getString("username"),
          resultSet.getString("email"),
          resultSet.getString("password_hash"),
          resultSet.getString("salt")
      );
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return null;
  }

  public List<User> getUserByUsername(String username) {
    String query = UserCRUDQueries.READ_BY_NAME.getQuery();
    List<User> users = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, username);
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        long id = resultSet.getLong("user_id");
        String userName = resultSet.getString("username");
        String email = resultSet.getString("email");
        String salt = resultSet.getString("salt");
        String hash = resultSet.getString("password_hash");
        users.add(new User(id, userName, email, salt, hash));
      }
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return users;
  }
}
