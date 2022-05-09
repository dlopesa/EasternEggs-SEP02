package database;

import utility.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConcreteItemDAO implements ItemDAO
{
  private static ConcreteItemDAO instance;
  private static Object lock = new Object();

  private ConcreteItemDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static ConcreteItemDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new ConcreteItemDAO();
        }
      }
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=cafe",
        "postgres", "1234");
    //Kamil's password 1234
    //Laura's password 123456
  }

  @Override public void createItem(Item item) throws SQLException
  {
    // when you are doing the Insert into the table, make sure to after Item,
    // provide the names of the columns you are inserting into so that it will know to auto increment the id.
    // INSERT INTO item(name,type,price,description)
    String name = item.getName();
    String type = item.getType();
    Double price = item.getPrice();
    String description = item.getDescription();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO item(name,type,price,description) VALUES(?,?,?,?);");
      statement.setString(1, name);
      statement.setString(2, type);
      statement.setDouble(3, price);
      statement.setString(4, description);
      statement.executeUpdate();
    }
    System.out.println("The adding of an item is completed.");
  }

  @Override public void readItemById(int id)
  {

  }

  @Override public void updateItem(int id, Item item)
  {

  }

  @Override public void deleteItem(int id)
  {

  }
}
