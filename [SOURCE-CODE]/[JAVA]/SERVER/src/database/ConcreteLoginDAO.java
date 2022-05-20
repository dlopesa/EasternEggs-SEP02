package database;

import utility.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConcreteLoginDAO implements LoginDAO
{

  private static ConcreteLoginDAO instance;
  private static Object lock = new Object();

  private ConcreteLoginDAO() throws SQLException
  {
    System.out.println("I'm stuck on ConcreteLoginDAO");
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static ConcreteLoginDAO getInstance() throws SQLException
  {
    System.out.println("I'm stuck on instance");
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new ConcreteLoginDAO();
        }
      }
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    //System.out.println("I'm sutck on connection");
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=cafe",
        "postgres", "1234");
    //Kamil's password 1234
    //Laura's password 123456
  }


  @Override public String getUserType(String pwd)
  {
    System.out.println("I'm stuck on getUserType");
    String userType = "default";
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT permission FROM accessKey where accessKey=?;");
      statement.setString(1, pwd);
      ResultSet accessKey = statement.executeQuery();
      while (accessKey.next())
      {
        userType = accessKey.getString("permission");
        System.out.println(userType);
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return userType;
  }

  @Override public ArrayList<AccessKey> getAllAccessKey() throws SQLException
  {
    ArrayList<AccessKey> accessKeys = new ArrayList<>();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM accessKey");
      ResultSet accessKeyResultSet = statement.executeQuery();
      while (accessKeyResultSet.next())
      {
        String accessKey = accessKeyResultSet.getString("accessKey");
        String permission = accessKeyResultSet.getString("permission");
        AccessKey newAk = new AccessKey(accessKey,permission);
        accessKeys.add(newAk);
      }
    }
    return accessKeys;
  }

  @Override public void removeAccessKey(AccessKey accessKey) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM accessKey WHERE accessKey = ?");
      deleteStatement.setString(1, accessKey.getAccessKey());
      deleteStatement.executeUpdate();
    }
  }

  @Override public void addAccessKey(AccessKey accessKey) throws SQLException
  {
    String accessKeyS = accessKey.getAccessKey();
    String permissionS = accessKey.getPermissionKey();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO accessKey(accessKey,permission) VALUES(?,?);");
      statement.setString(1, accessKeyS);
      statement.setString(2, permissionS);
      statement.executeUpdate();
    }
    System.out.println("The adding of an accessKey is completed.");
  }
}
