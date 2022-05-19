package mediator;

import utility.Extra;
import utility.AccessKey;
import utility.Item;
import utility.ItemList;
import utility.Order;
import utility.observer.event.ObserverEvent;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;
import utility.observer.listener.RemoteListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class RemoteClient
    implements RemoteListener<String, String>, UnnamedPropertyChangeSubject
{
  private RemoteCafeServer server;
  private PropertyChangeSupport property;

  public RemoteClient()
      throws MalformedURLException, NotBoundException, RemoteException
  {
    server = (RemoteCafeServer) Naming.lookup("rmi://localhost:1099/Cafe");
    UnicastRemoteObject.exportObject(this, 0);
    server.addListener(this);
    property = new PropertyChangeSupport(this);
  }

  public ItemList getAllItems() throws RemoteException, SQLException
  {
    return server.getAllItems();
  }

  public ItemList getItemsByType(String type)
      throws RemoteException, SQLException
  {
    return server.getItemsByType(type);
  }

  public ArrayList<AccessKey> getAllAccessKey()
      throws RemoteException, SQLException
  {
    return server.getAllAccessKey();
  }

  public int receiveOrder(Order order) throws RemoteException
  {
    return server.receiveOrder(order);
  }

  public void completeOrder(Order order) throws RemoteException
  {
    server.completeOrder(order);
  }

  public void cancelOrder(Order order) throws RemoteException
  {
    server.cancelOrder(order);
  }

  public void editCommentInOrder(Order order, String comment)
      throws RemoteException
  {
    server.editCommentInOrder(order, comment);
  }

  public void receiveUnpaidOrder(Order order) throws RemoteException
  {
    server.receiveUnpaidOrder(order);
  }

  public void acceptPayment(Order order) throws RemoteException
  {
    server.acceptPayment(order);
  }

  public void addItemToProductList(Item item) throws RemoteException
  {
    server.addItemToProductList(item);
  }

  public void addAccessKey(AccessKey accessKey)
  {
    server.addAccessKey(accessKey);
  }

  public ArrayList<Order> getAllPendingOrders() throws RemoteException
  {
    return server.getAllPendingOrders();
  }

  public ArrayList<Order> getAllUnpaidOrders() throws RemoteException
  {
    return server.getAllUnpaidOrders();
  }

  public ArrayList<Order> getAllCompletedOrders() throws RemoteException
  {
    return server.getAllCompletedOrders();
  }

  public void removeItemFromProductList(Item item)
      throws RemoteException, SQLException
  {
    server.removeItemFromProductList(item);
  }

  public ArrayList<Extra> getAllExtrasByType(String type) throws RemoteException
  {
    return server.getAllExtrasByType(type);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

  @Override public void propertyChange(ObserverEvent<String, String> event)
      throws RemoteException
  {
    property.firePropertyChange("change", event.getValue1(), event.getValue2());
  }

  public void removeAccessKey(AccessKey accessKey) throws RemoteException
  {
    server.removeAccessKey(accessKey);
  }

  public String getUserType(String pwd) throws RemoteException, SQLException
  {
    System.out.println("Client|From Client: " + pwd);
    String ak = server.getUserType(pwd);
    System.out.println("Client|From Server: " + ak);
    return ak;
  }

}
