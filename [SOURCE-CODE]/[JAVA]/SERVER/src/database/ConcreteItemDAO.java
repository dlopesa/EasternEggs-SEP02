package database;

import utility.Item;

import java.sql.DriverManager;
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

  @Override public void createItem(Item item)
  {
    // when you are doing the Insert into the table, make sure to after Item,
    // provide the names of the columns you are inserting into so that it will know to auto increment the id.
    // INSERT INTO item(name,type,price,description)
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
