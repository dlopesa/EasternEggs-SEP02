package model;

import mediator.RemoteClient;
import utility.Extra;
import utility.Item;
import utility.ItemList;
import utility.Order;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelManager implements Model
{

  private RemoteClient client;
  private Order order;

  public ModelManager() throws MalformedURLException, NotBoundException, RemoteException
  {
    client = new RemoteClient();
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
    if (order == null)
    {
      order = new Order(false);
    }
    System.out.println("I am adding an item: " + item);
    order.addItem(item);
  }

  @Override public void submitOrder() throws NullPointerException
  {
    try
    {
      System.out.println("I am submitting the order");

      client.receiveOrder(order);
      System.out.println("I have submitted the order");
    }

    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addExtraToItem(Extra extra, Item item)
  {

  }

  @Override public void removeExtraFromItem(Extra extra, Item item)
  {

  }

  @Override public void removeItemFromOrder(Item item)
  {

  }

  @Override public Order getOrder()
  {
    return order;
  }

  @Override public ItemList getAllExistingItems()
  {
    return null;
  }

  @Override public void setComment(String comment)
  {

  }

  @Override public void cancelOrder()
  {

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
      System.out.println("ModelManager: Item sent to the mediator.");
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void removeItemFromProductList(Item item)
  {

  }

  @Override public ArrayList<Order> getAllPendingOrders()
  {
    // TODO: 04/05/2022 UPDATE THE RETURN TYPE IN THE CLASS DIAGRAM
    return null;
  }

  @Override public void completeOrder(Order order)
  {

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
}
