package database;

import utility.DateTime;
import utility.Extra;
import utility.Item;
import utility.ItemList;

import java.sql.*;
import java.util.ArrayList;

public class ConcreteExtraDAO implements ExtraDAO
{
  private static ConcreteExtraDAO instance;
  private static Object lock = new Object();

  private ConcreteExtraDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static ConcreteExtraDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new ConcreteExtraDAO();
        }
      }
    }
    return instance;
  }

  @Override public ArrayList<Extra> getExtrasByType(String type) throws SQLException
  {
    ArrayList<Extra> extrasList = new ArrayList<>();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM extraavailablefortype WHERE type = ?");
      statement.setString(1, type);
      ResultSet orderResultSet = statement.executeQuery();
      while (orderResultSet.next())
      {
        String name = orderResultSet.getString("extra_id");
        extrasList.add(new Extra(name));
      }
    }
    return extrasList;
  }

  @Override public void create(Extra extra) throws SQLException
  {
    String name = extra.getName();
    ArrayList<String> availableTypes = extra.getAvailableTypes();
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(""
          + "INSERT INTO extra(name) VALUES (?);");
      statement.setString(1,name);
      statement.executeUpdate();

      for (String type : availableTypes) {
        PreparedStatement statement2 = connection.prepareStatement(""
            + "INSERT INTO extraavailablefortype(extra_id, type) VALUES (?,?);");
        statement2.setString(1,name);
        statement2.setString(2,type);
        statement2.executeUpdate();
      }
    }
  }

  @Override public void delete(Extra extra) throws SQLException
  {
    String name = extra.getName();
    try (Connection connection = getConnection())
    {
      PreparedStatement deleteStatement = connection.prepareStatement(
          "DELETE FROM extra WHERE name = ?");
      deleteStatement.setString(1, name);
      deleteStatement.executeUpdate();
    }
  }

  @Override public ArrayList<Extra> getAllExtras() throws SQLException
  {

    ArrayList<Extra> extras = new ArrayList<>();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM extra;");
      ResultSet extraResultSet = statement.executeQuery();
      while (extraResultSet.next())
      {
        String name = extraResultSet.getString("name");
        Extra extra = new Extra(name);

        PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM extraavailablefortype WHERE extra_id = ?;");
        statement2.setString(1,name);
        ResultSet extraAvailableForType = statement2.executeQuery();
        while (extraAvailableForType.next()) {
          String type = extraAvailableForType.getString("type");
          extra.addAvailableType(type);
        }

        extras.add(extra);
      }
    }
    return extras;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=cafe", "postgres", "root");

  }

}
