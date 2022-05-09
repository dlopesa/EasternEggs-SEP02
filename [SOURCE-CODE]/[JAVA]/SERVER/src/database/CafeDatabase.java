package database;

import utility.Item;
import utility.ItemList;
import utility.Order;

import java.sql.SQLException;

public class CafeDatabase implements CafePersistence
{
  private static CafeDatabase instance;
  private static Object lock = new Object();
  private OrderDAO orderDAO;
  private ItemDAO itemDAO;

  private CafeDatabase()
  {
    try
    {
      orderDAO = ConcreteOrderDAO.getInstance();
      itemDAO = ConcreteItemDAO.getInstance();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

  }

  public static CafeDatabase getInstance()
  {

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
    try
    {
      orderDAO.create(order);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public void completeOrder(int orderId)
  {
    try
    {
      orderDAO.updateStatus(orderId, "completed");
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
      orderDAO.updateStatus(orderId, "pending");
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
      orderDAO.updateComment(orderId, comment);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addItemToProductList(Item item)
  {
    try
    {
      itemDAO.createItem(item);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
}
