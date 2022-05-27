package model;

import mediator.RemoteClient;
import utility.Extra;
import utility.Item;
import utility.ItemList;
import utility.Order;
import utility.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManager implements Model
{

  private RemoteClient client;
  private Order order;
  private ArrayList<String> types;
  private ArrayList<String> permissions;
  private PropertyChangeSupport property;

  /**
   * A multi arguments constructor, that also create and adds to different Strings, +so they can be shown on the views
   * @throws MalformedURLException
   * @throws NotBoundException
   * @throws RemoteException
   */
  public ModelManager() throws MalformedURLException, NotBoundException, RemoteException
  {
    client = new RemoteClient();
    client.addListener(this);
    types = new ArrayList<>();
    types.add("coffee");
    types.add("tea");
    types.add("snack");
    types.add("smoothie");
    permissions = new ArrayList<>();
    permissions.add("Barista");
    permissions.add("Cashier");
    permissions.add("Display");
    permissions.add("Admin");
    order = new Order(false);
    property = new PropertyChangeSupport(this);
    order.addListener(this);
  }
//TODO ?? does nothing
  @Override public void setUserType(String type)
  {

  }

  @Override public String getUserType(String pwd)

  {
    String ak = null;
    try
    {
      ak = client.getUserType(pwd);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return ak;
  }

  /**
   * Method that requests to the order to add an item
   * @param item
   */
  @Override public void addItemToOrder(Item item)
  {
    order.addItem(item);
  }

  /**
   * Method that requests to an order to be submitted
   * @return
   * @throws NullPointerException
   */
  @Override public int submitOrder() throws NullPointerException
  {
    int tempId = -20;
    try
    {
      System.out.println("Model: Sending the order to the database: the following methods have already been tested and are working. The order should be displayed in the database");
      tempId = client.receiveOrder(order);

      quitAndCancelOrder();

    }

    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return tempId;
  }

  /**
   * Method that requests to an extra to be added intro an item
   * @param extra
   * @param item
   */
  @Override public void addExtraToItem(Extra extra, Item item)
  {
    item.addExtra(extra);
  }

  /**
   * Method that requests to an extra to be removed from an item
   * @param extra
   * @param item
   */
  @Override public void removeExtraFromItem(Extra extra, Item item)
  {
    item.removeExtra(extra);
  }

  /**
   * Method that requests to an item to be removed from an order
   * @param item
   */
  @Override public void removeItemFromOrder(Item item)
  {
    order.removeItem(item);
  }

  @Override public Order getOrder()
  {
    return order;
  }

  /**
   * Method that gets all the existing items
   * @return
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public ItemList getAllExistingItems() throws RemoteException, SQLException
  {
    return client.getAllItems();
  }


  @Override public ArrayList<AccessKey> getAllAccessKey()
  {
    try
    {
      return client.getAllAccessKey();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
 //TODO Does nothing?
  @Override public void setComment(String comment)
  {

  }

  /**
   * Method that quits and cancel an order
   */
  @Override public void quitAndCancelOrder()
  {
    order = new Order(false);
    order.addListener(this);
    property.firePropertyChange("cancel", order.getPrice(), null);
  }

  /**
   * Method that requests to an order be marked as paid, if cash mark as unpaid
   * @param isCash
   * @return
   */
  @Override public int payForOrder(boolean isCash)
  {
    if (isCash)
    {
      order.setStatus("unpaid");
    }
    return submitOrder();
  }

  /**
   * Method that sets an order to be marked as take away
   */
  @Override public void setIsTakeAway()
  {
    order.setIsTakeAway();
  }

  /**
   * Requests to an item to be added to an product list
   * @param item
   */
  @Override public void addItemToProductList(Item item)
  {
    try
    {
      client.addItemToProductList(item);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * A method that requests to an access key to be created
   * @param accessKey
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public void addAccessKey(AccessKey accessKey)
      throws SQLException, RemoteException
  {
    client.addAccessKey(accessKey);
  }

  /**
   * A method that requests to an item to e removed from an item list
   * @param item
   */
  @Override public void removeItemFromProductList(Item item)
  {
    try
    {
      client.removeItemFromProductList(item);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * A method thar requests to an access key to be removed
   * @param accessKey
   */
  @Override public void removeAccessKey(AccessKey accessKey)
  {
    try
    {
      client.removeAccessKey(accessKey);

    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * A method that gets all the pending orders
   * @returns all pending orders
   */
  @Override public ArrayList<Order> getAllPendingOrders()
  {
    try
    {
      return client.getAllPendingOrders();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;

  }

  /**
   * A method that gets all unpaid orders
   * @returns all unpaid orders
   */
  @Override public ArrayList<Order> getAllUnpaidOrders()
  {
    try
    {
      return client.getAllUnpaidOrders();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  /**
   * Method that gets all completed orders
   * @returns all completed orders
   */
  public ArrayList<Order> getAllCompletedOrders()
  {
    try
    {
      return client.getAllCompletedOrders();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  /**
   * Method that requests an order to be completed
   * @param order
   * @throws RemoteException
   */
  @Override public void completeOrder(Order order) throws RemoteException
  {
    client.completeOrder(order);
  }

  /**
   * Method that accepts a payment
   * @param order
   */
  @Override public void acceptPayment(Order order)
  {
    try
    {
      client.acceptPayment(order);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Method that makes possible to a cashier to edit an order's comment
   * @param order
   * @param comment
   */
  @Override public void editOrderCommentByCashier(Order order, String comment)
  {
    try
    {
      client.editCommentInOrder(order, comment);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * A method that makes possible to a costumer edt his own order
   * @param comment
   */
  @Override public void editOrderCommentByCustomer(String comment)
  {
    order.setComment(comment);
  }

  /**
   * A method that cancels an unpaid order
   * @param order
   */
  @Override public void cancelUnpaidOrder(Order order)
  {
    try
    {
      client.cancelOrder(order);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * A method that gets all the types
   * @returns an array list with all the types
   */
  @Override public ArrayList<String> getAllTypes()
  {
    return types;
  }

  /**
   * A method that gets all the extras by type
   * @param type
   * @return
   */
  @Override public ArrayList<Extra> getAllExtrasByType(String type)
  {

    try
    {
      return client.getAllExtrasByType(type);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Gets all the type of permissions
   * @returns an arraylist with all the type of permissions
   */

  @Override public ArrayList<String> getAllPermissions()
  {
    return permissions;
  }

  /**
   * A method that gets all the extras
   * @returns an array list with all the extras
   */
  @Override public ArrayList<Extra> getAllExtras()
  {
    try
    {
      return client.getAllExtras();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * A method that adds an extra to an extra list
   * @param extra
   */
  @Override public void addExtraToExtraList(Extra extra)
  {
    try
    {
      client.addExtraToExtraList(extra);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * A method that removes an extra from an extra list
   * @param extra
   */
  @Override public void removeExtraFromExtraList(Extra extra)
  {
    try
    {
      client.removeExtraFromExtraList(extra);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Fires an listener
   * @param evt
   */
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    property.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
  }

  /**
   * A method that adds an listener
   * @param listener
   */
  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  /**
   * A method removes that removes a listener
   * @param listener
   */
  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
