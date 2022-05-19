package model;

import mediator.RemoteClient;
import utility.Extra;
import utility.Item;
import utility.ItemList;
import utility.Order;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

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
  private PropertyChangeSupport property;

  public ModelManager() throws MalformedURLException, NotBoundException, RemoteException
  {
    client = new RemoteClient();
    types = new ArrayList<>();
    types.add("coffee");
    types.add("tea");
    types.add("snack");
    types.add("smoothie");
    order = new Order(false);
    property = new PropertyChangeSupport(this);
    order.addListener(this);
  }

  @Override public void setUserType(String type)
  {

  }

  @Override public String getUserType()
  {
    return null;
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

      cancelOrder();

    }

    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return tempId;
  }

  @Override public void addExtraToItem(Extra extra, Item item)
  {

  }

  @Override public void removeExtraFromItem(Extra extra, Item item)
  {

  }

  @Override public void removeItemFromOrder(Item item)
  {
    order.removeItem(item);
  }

  @Override public Order getOrder()
  {
    return order;
  }

  @Override public ItemList getAllExistingItems() throws RemoteException, SQLException
  {
    return client.getAllItems();
  }

  @Override public void setComment(String comment)
  {

  }

  @Override public void cancelOrder()
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

  @Override public void removeItemFromProductList(Item item)
  {
    try
    {
      client.removeItemFromProductList(item);

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

  @Override public void completeOrder(Order order) throws RemoteException
  {
    client.completeOrder(order);
  }

  @Override public void acceptOrder(Order order)
  {

  }

  @Override public void editOrderComment(Order order)
  {

  }

  @Override public void cancelUnpaidOrder(Order order)
  {

  }

  @Override public ArrayList<String> getAllTypes()
  {
    return types;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    property.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
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
