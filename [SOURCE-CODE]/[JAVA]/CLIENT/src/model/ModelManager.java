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

  public ModelManager()
      throws MalformedURLException, NotBoundException, RemoteException
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

  @Override public void setUserType(String type)
  {

  }

  @Override public String getUserType(String pwd)

  {
    System.out.println("Model|From View: " + pwd);
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
    System.out.println("Model|From Client: " + ak);
    return ak;
  }

  @Override public void addItemToOrder(Item item)
  {
    order.addItem(item);
  }

  @Override public int submitOrder() throws NullPointerException
  {
    int tempId = -20;
    try
    {
      tempId = client.receiveOrder(order);

      quitAndCancelOrder();

    }

    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return tempId;
  }

  @Override public void addExtraToItem(Extra extra, Item item)
  {
    item.addExtra(extra);
    System.out.println("Model maanaad a: " + item);
  }

  @Override public void removeExtraFromItem(Extra extra, Item item)
  {
    item.removeExtra(extra);
  }



  @Override public void removeItemFromOrder(Item item)
  {
    order.removeItem(item);
  }

  @Override public Order getOrder()
  {
    return order;
  }

  @Override public ItemList getAllExistingItems()
      throws RemoteException, SQLException
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


  @Override public void setComment(String comment)
  {

  }

  @Override public void quitAndCancelOrder()
  {
    order = new Order(false);
    order.addListener(this);
    property.firePropertyChange("cancel", order.getPrice(), null);
  }

  @Override public void payForOrder(boolean isCash)
  {

  }

  @Override public void setIsTakeAway(boolean isTakeAway)
  {

  }

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

  @Override public void addAccessKey(AccessKey accessKey)
      throws SQLException, RemoteException
  {
    client.addAccessKey(accessKey);
  }


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

  @Override public void completeOrder(Order order) throws RemoteException
  {
    client.completeOrder(order);
  }

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

  @Override public void editOrderComment(Order order, String comment)
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

  @Override public ArrayList<String> getAllTypes()
  {
    return types;
  }

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
  @Override public ArrayList<String> getAllPermissions()
  {
    return permissions;
  }

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

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    property.firePropertyChange(evt.getPropertyName(), evt.getOldValue(),
        evt.getNewValue());
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
