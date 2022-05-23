package database;

import utility.Extra;
import utility.AccessKey;
import utility.Item;
import utility.ItemList;
import utility.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class CafeDatabase implements CafePersistence
{
  private static CafeDatabase instance;
  private static Object lock = new Object();
  private OrderDAO orderDAO;
  private ItemDAO itemDAO;
  private ExtraDAO extraDAO;
  private LoginDAO loginDAO;

  private CafeDatabase()
  {
    try
    {
      orderDAO = ConcreteOrderDAO.getInstance();
      itemDAO = ConcreteItemDAO.getInstance();
      extraDAO= ConcreteExtraDAO.getInstance();
      loginDAO = ConcreteLoginDAO.getInstance();
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

  @Override public ItemList getAllItems() throws SQLException
  {
    return itemDAO.getAllItems();
  }

  @Override public ItemList getItemsByType(String type)
  {
   return null; //TODO this method does nothing. it should. but that's ok
  }
  @Override public ArrayList<AccessKey> getAllAccessKey() throws SQLException
  {
    try
    {
      return loginDAO.getAllAccessKey();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public int receiveOrder(Order order)
  {
    try
    {
      return orderDAO.create(order);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return -10;
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

  @Override public void cancelOrder(int orderId)
  {
    try
    {
      orderDAO.delete(orderId);
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
  @Override public void addAccessKey(AccessKey accessKey)
  {
    try
    {
      loginDAO.addAccessKey(accessKey);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public ArrayList<Order> getOrdersByStatus(String status)
  {

    try
    {
      return orderDAO.getOrdersByStatus(status);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

    @Override public void removeItemFromProductList (Item item) throws
    SQLException {
    itemDAO.deleteItem(item);
  }

  @Override public ArrayList<Extra> getAllExtrasByType(String type) throws SQLException
  {
    return extraDAO.getExtrasByType(type);
  }
  @Override public void removeAccessKey(AccessKey accessKey) throws SQLException
  {
    loginDAO.removeAccessKey(accessKey);
  }

  @Override public String getUserType(String pwd) throws SQLException
  {
    String ak;
    ak = loginDAO.getUserType(pwd);
    return ak;
  }
}
