package org.example.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.example.dao.UserDAOImpl;
import org.example.model.User;

public class UserService {

  UserDAOImpl userDAO;

  public UserService() {
    userDAO = new UserDAOImpl();
  }

  public void createUser(User user, String password) {
    try {
      hashingPassword(user, password);
    } catch (InvalidKeySpecException e) {
      System.out.println("Something went wrong with hashing mechanism");
    }
    userDAO.insert(user);
  }

  private static void hashingPassword(User user, String password) throws InvalidKeySpecException {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);

    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
    SecretKeyFactory factory = null;
    try {
      factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    byte[] hash = factory.generateSecret(spec).getEncoded();

    String saltStr = Base64.getEncoder().encodeToString(salt);
    String hashStr = Base64.getEncoder().encodeToString(hash);

    user.setHash(hashStr);
    user.setSalt(saltStr);
  }

  public void deleteUser(int id) {
    userDAO.delete(id);
  }

  public void updateUser(User user, String password) {
    try {
      hashingPassword(user, password);
    } catch (InvalidKeySpecException e) {
      System.out.println("Something went wrong with hashing mechanism");
    }
    userDAO.update(user);
  }

  public User getUser(int id) {
    return userDAO.get(id);
  }

  public List<User> getAllUsers() {
    return userDAO.getAll();
  }

  public Boolean checkUserPasswordByName(String name, String password) {
    List<User> userList = userDAO.getUserByUsername(name);

    if (userList.isEmpty()) {
      System.out.println("There is no user with username " + name);
      return false;
    }

    if (userList.size() > 1) {
      System.out.println("There are more than one user with username " + name + ". Try to check user password by User ID");
      return false;
    }

    return validatePassword(password, userList.get(0));
  }

  public Boolean checkUserPasswordById(long id, String password) {
    User user = userDAO.get(id);

    if (user == null) {
      System.out.println("There is no user with id " + id);
      return false;
    }

    return validatePassword(password, user);
  }

  private static Boolean validatePassword(String password, User user) {
    try {
      if (user.getSalt() == null || user.getHash() == null) {
        System.out.println("It looks like there is no password for such user");
        return false;
      }
      // Decode the stored salt and hash
      byte[] salt = Base64.getDecoder().decode(user.getSalt());
      byte[] storedHash = Base64.getDecoder().decode(user.getHash());

      // Hash the input password with the same parameters
      KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      byte[] hash = factory.generateSecret(spec).getEncoded();

      // Compare the stored hash and the generated hash
      return MessageDigest.isEqual(storedHash, hash);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      System.out.println("Error during password validation: " + e.getMessage());
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("Error decoding base64: " + e.getMessage());
      return false;
    }
  }
}
