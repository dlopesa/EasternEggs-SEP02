package database;

import java.sql.SQLException;

public class GetAllItemsTest
{
  public static void main(String[] args) throws SQLException
  {
    System.out.println(ConcreteItemDAO.getInstance().getAllItems());;
  }
}
