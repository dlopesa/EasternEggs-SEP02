package database;

import utility.DateTime;
import utility.Extra;

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

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=cafe", "postgres", "robertbarta");

  }

}
