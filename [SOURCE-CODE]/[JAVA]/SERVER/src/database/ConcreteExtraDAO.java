package database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConcreteExtraDAO implements ExtraDAO
{
  private static ConcreteExtraDAO instance;
  private static Object lock= new Object();

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
       if (instance==null)
       {
         instance= new ConcreteExtraDAO();
       }
     }
    }
    return instance;
  }


  @Override public void getExtrasByType(String type) throws SQLException
  {
  }
}
