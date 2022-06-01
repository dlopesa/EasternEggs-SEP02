package model;

import utility.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserProxy implements Model
{
  private ModelManager modelManager;
  private String userType;
  public static String CUSTOMER = "customer";
  public static String ADMIN = "admin";
  public static String BARISTA = "barista";
  public static String CASHIER = "cashier";
  public static String DISPLAY = "display";

  public UserProxy(ModelManager modelManager)
  {
    this.modelManager = modelManager;
  }

  @Override public void setUserType(String type)
  {
    this.userType = type;
  }

  @Override public String getUserType(String pwd)
  {
    return modelManager.getUserType(pwd);
  }

  @Override public void addItemToOrder(Item item) throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      modelManager.addItemToOrder(item);
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
      return modelManager.submitOrder();
    }
    else
      throw new IllegalAccessException("Only a customer can submit an order.");
  }

  @Override public void addExtraToItem(Extra extra, Item item)
      throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      modelManager.addExtraToItem(extra, item);
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
      modelManager.removeExtraFromItem(extra, item);
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
      modelManager.removeAccessKey(accessKey);
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
      modelManager.removeItemFromOrder(item);
    }
    else
      throw new IllegalAccessException(
          "Only a customer can remove an item from an order.");
  }

  @Override public Order getOrder() throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      return modelManager.getOrder();
    }
    else
      throw new IllegalAccessException("Only a customer can return an order.");
  }

  @Override public ItemList getAllExistingItems()
      throws RemoteException, SQLException, IllegalAccessException
  {
    if (userType.equals(CUSTOMER) || userType.equals(ADMIN))
    {
      return modelManager.getAllExistingItems();
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
      return modelManager.getAllAccessKey();
    }
    else
      throw new IllegalAccessException(
          "Only admins have access to all access keys.");
  }

  @Override public void quitAndCancelOrder() throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      modelManager.quitAndCancelOrder();
    }
    else
      throw new IllegalAccessException(
          "Only customers can quit and cancel their order.");
  }

  @Override public int payForOrder(boolean isCash) throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      return modelManager.payForOrder(isCash);
    }
    else
      throw new IllegalAccessException(
          "Only customers can pay for their orders.");
  }

  @Override public void setIsTakeAway() throws IllegalAccessException
  {
    if (userType.equals(CUSTOMER))
    {
      modelManager.setIsTakeAway();
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
      modelManager.addItemToProductList(item);
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
      modelManager.addAccessKey(accessKey);
    }
    else
      throw new IllegalAccessException("Only admins can add a new access key.");
  }

  @Override public void removeItemFromProductList(Item item)
      throws IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      modelManager.removeItemFromProductList(item);
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
      return modelManager.getAllPendingOrders();
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
      return modelManager.getAllUnpaidOrders();
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
      return modelManager.getAllCompletedOrders();
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
      modelManager.completeOrder(order);
    }
    else
      throw new IllegalAccessException("Only barista can complete an order.");
  }

  @Override public void acceptPayment(Order order) throws IllegalAccessException
  {
    if (userType.equals(CASHIER))
    {
      modelManager.acceptPayment(order);
    }
    else
      throw new IllegalAccessException("Only cashier can accept a payment.");
  }

  @Override public void editOrderCommentByCashier(Order order, String comment)
      throws IllegalAccessException
  {
    if (userType.equals(CASHIER))
    {
      modelManager.editOrderCommentByCashier(order, comment);
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
      modelManager.editOrderCommentByCustomer(comment);
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
      modelManager.cancelUnpaidOrder(order);
    }
    else
      throw new IllegalAccessException(
          "Only cashier can cancel an unpaid order");
  }

  @Override public ArrayList<String> getAllTypes() throws IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      return modelManager.getAllTypes();
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
      return modelManager.getAllExtrasByType(type);
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
      return modelManager.getAllPermissions();
    }
    else
      throw new IllegalAccessException(
          "Only admin can get all permissions.");
  }

  @Override public ArrayList<Extra> getAllExtras() throws IllegalAccessException
  {
    if (userType.equals(ADMIN))
    {
      return modelManager.getAllExtras();
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
      modelManager.addExtraToExtraList(extra);
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
      modelManager.removeExtraFromExtraList(extra);
    }
    else
      throw new IllegalAccessException(
          "Only admin can remove an extra from the extra list.");
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    modelManager.propertyChange(evt);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    modelManager.addListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    modelManager.removeListener(listener);
  }
}
