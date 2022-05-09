package database;

import utility.ItemList;
import utility.Order;

import java.sql.SQLException;

public class CafeDatabase implements CafePersistence
{
  private static CafeDatabase instance;
  private static Object lock = new Object();

  private CafeDatabase() {}

  public static CafeDatabase getInstance() {

    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new CafeDatabase();
        }
      }
    }
    return instance;
  }



  @Override public ItemList getAllItems()
  {
    return null; //TODO item dao here
  }

  @Override public ItemList getItemsByType()
  {
    return null; //TODO item dao here
  }

  @Override public void receiveOrder(Order order)
  {
    try {
      ConcreteOrderDAO.getInstance().create(order);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override public void completeOrder(int orderId)
  {
    try {
      ConcreteOrderDAO.getInstance().updateStatus(orderId, "completed");
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void acceptPayment(int orderId)
  {
    try
    {
      ConcreteOrderDAO.getInstance().updateStatus(orderId,"pending");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void editComment(int orderId, String comment)
  {
    try
    {
      ConcreteOrderDAO.getInstance().updateComment(orderId, comment);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
}
