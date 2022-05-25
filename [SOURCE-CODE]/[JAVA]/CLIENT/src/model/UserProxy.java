package model;

import utility.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserProxy implements Model
{
  private Model model;
  private String userType;
  public static String CUSTOMER = "customer";
  public static String ADMIN = "admin";
  public static String BARISTA = "barista";
  public static String CASHIER = "cashier";
  public static String DISPLAY = "display";

  public UserProxy(Model model)
  {
    this.model = model;
  }

  @Override public void setUserType(String type)
  {
    this.userType = type;
  }

  @Override public String getUserType(String pwd)
  {
    return model.getUserType(pwd);
  }

  @Override public void addItemToOrder(Item item) throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      model.addItemToOrder(item);
    }
    else
      throw new IllegalAccessException(
          "Only a customer can add an item to the Client order.");
  }

  @Override public int submitOrder()
      throws NullPointerException, IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      return model.submitOrder();
    }
    else
      throw new IllegalAccessException("Only a customer can submit an order.");
  }

  @Override public void addExtraToItem(Extra extra, Item item)
      throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      model.addExtraToItem(extra, item);
    }
    else
      throw new IllegalAccessException(
          "Only a customer can add an extra to an item.");
  }

  @Override public void removeExtraFromItem(Extra extra, Item item)
      throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      model.removeExtraFromItem(extra, item);
    }
    else
      throw new IllegalAccessException(
          "Only a customer can remove an extra from an item.");
  }

  @Override public void removeAccessKey(AccessKey accessKey)
      throws IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      model.removeAccessKey(accessKey);
    }
    else
      throw new IllegalAccessException(
          "Only an admin can remove an access key.");
  }

  @Override public void removeItemFromOrder(Item item)
      throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      model.removeItemFromOrder(item);
    }
    else
      throw new IllegalAccessException(
          "Only a customer can remove an item from an order.");
  }

  @Override public Order getOrder() throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      return model.getOrder();
    }
    else
      throw new IllegalAccessException("Only a customer can return an order.");
  }

  @Override public ItemList getAllExistingItems()
      throws RemoteException, SQLException, IllegalAccessException
  {
    if (userType.equals(CUSTOMER) || userType.equals(ADMIN))
    {
      return model.getAllExistingItems();
    }
    else
      throw new IllegalAccessException(
          "Only customers or admins have access to all items.");
  }

  @Override public ArrayList<AccessKey> getAllAccessKey()
      throws RemoteException, SQLException, IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      return model.getAllAccessKey();
    }
    else
      throw new IllegalAccessException(
          "Only admins have access to all access keys.");
  }

  @Override public void quitAndCancelOrder() throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      model.quitAndCancelOrder();
    }
    else
      throw new IllegalAccessException(
          "Only customers can quit and cancel their order.");
  }

  @Override public int payForOrder(boolean isCash) throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      return model.payForOrder(isCash);
    }
    else
      throw new IllegalAccessException(
          "Only customers can pay for their orders.");
  }

  @Override public void setIsTakeAway() throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      model.setIsTakeAway();
    }
    else
      throw new IllegalAccessException(
          "Only customers can make their orders TAKE-AWAY.");
  }

  @Override public void addItemToProductList(Item item)
      throws IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      model.addItemToProductList(item);
    }
    else
      throw new IllegalAccessException(
          "Only admins can add items to product list.");
  }

  @Override public void addAccessKey(AccessKey accessKey)
      throws SQLException, RemoteException, IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      model.addAccessKey(accessKey);
    }
    else
      throw new IllegalAccessException("Only admins can add a new access key.");
  }

  @Override public void removeItemFromProductList(Item item)
      throws IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      model.removeItemFromProductList(item);
    }
    else
      throw new IllegalAccessException(
          "Only admins can remove an item from the product list.");
  }

  @Override public ArrayList<Order> getAllPendingOrders()
      throws IllegalAccessException
  {
    if (userType.equals(BARISTA) || userType.equals(DISPLAY) || userType.equals(
        CASHIER))
    {
      return model.getAllPendingOrders();
    }
    else
      throw new IllegalAccessException(
          "Only baristas, cashiers or display can get all pending orders.");
  }

  @Override public ArrayList<Order> getAllUnpaidOrders()
      throws IllegalAccessException
  {

    if (userType.equals(CASHIER))
    {
      return model.getAllUnpaidOrders();
    }
    else
      throw new IllegalAccessException(
          "Only cashiers can get all unpaid orders.");
  }

  @Override public ArrayList<Order> getAllCompletedOrders()
      throws IllegalAccessException
  {
    if (userType.equals(DISPLAY))
    {
      return model.getAllCompletedOrders();
    }
    else
      throw new IllegalAccessException(
          "Only display can get all completed orders.");
  }

  @Override public void completeOrder(Order order)
      throws RemoteException, IllegalAccessException
  {
    if (userType.equals(BARISTA))
    {
      model.completeOrder(order);
    }
    else
      throw new IllegalAccessException("Only barista can complete an order.");
  }

  @Override public void acceptPayment(Order order) throws IllegalAccessException
  {
    if (userType.equals(CASHIER))
    {
      model.acceptPayment(order);
    }
    else
      throw new IllegalAccessException("Only cashier can accept a payment.");
  }

  @Override public void editOrderCommentByCashier(Order order, String comment)
      throws IllegalAccessException
  {
    if (userType.equals(CASHIER))
    {
      model.editOrderCommentByCashier(order, comment);
    }
    else
      throw new IllegalAccessException(
          "Only cashier can edit a comment like this.");
  }

  @Override public void editOrderCommentByCustomer(String comment)
      throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      model.editOrderCommentByCustomer(comment);
    }
    else
      throw new IllegalAccessException(
          "Only a customer can edit a comment like this.");
  }

  @Override public void cancelUnpaidOrder(Order order)
      throws IllegalAccessException
  {
    if (userType.equals(CASHIER))
    {
      model.cancelUnpaidOrder(order);
    }
    else
      throw new IllegalAccessException(
          "Only cashier can cancel an unpaid order");
  }

  @Override public ArrayList<String> getAllTypes() throws IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      return model.getAllTypes();
    }
    else
      throw new IllegalAccessException(
          "Only admin can get all types");
  }

  @Override public ArrayList<Extra> getAllExtrasByType(String type)
      throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      return model.getAllExtrasByType(type);
    }
    else
      throw new IllegalAccessException(
          "Only a customer can get all extras for a certain type.");
  }

  @Override public ArrayList<String> getAllPermissions()
      throws IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      return model.getAllPermissions();
    }
    else
      throw new IllegalAccessException(
          "Only admin can get all permissions.");
  }

  @Override public ArrayList<Extra> getAllExtras() throws IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      return model.getAllExtras();
    }
    else
      throw new IllegalAccessException(
          "Only admin can get all extras.");
  }

  @Override public void addExtraToExtraList(Extra extra)
      throws IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      model.addExtraToExtraList(extra);
    }
    else
      throw new IllegalAccessException(
          "Only admin can add an extra to the extra list.");
  }

  @Override public void removeExtraFromExtraList(Extra extra)
      throws IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      model.removeExtraFromExtraList(extra);
    }
    else
      throw new IllegalAccessException(
          "Only admin can remove an extra from the extra list.");
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    model.propertyChange(evt);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    model.addListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    model.removeListener(listener);
  }
}
