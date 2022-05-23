package mediator;

import database.CafeDatabase;
import database.CafePersistence;
import database.ConcreteOrderDAO;
import database.OrderDAO;
import utility.Extra;
import utility.AccessKey;
import utility.Item;
import utility.ItemList;
import utility.Order;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class RemoteServer implements RemoteCafeServer

{
  private CafePersistence cafePersistence;
  private PropertyChangeHandler<String, String> property;

  public RemoteServer()
      throws RemoteException, MalformedURLException, SQLException
  {
    cafePersistence = CafeDatabase.getInstance();
    property = new PropertyChangeHandler<>(this);
    startRegistry();
    startServer();
  }

  private void startRegistry() throws RemoteException
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    }
    catch (java.rmi.server.ExportException e)
    {
      System.out.println("Registry already started? " + e.getMessage());
    }
  }

  private void startServer() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("Cafe", this);
    System.out.println("Server started...");
  }

  @Override public ItemList getAllItems() throws RemoteException, SQLException
  {
    return cafePersistence.getAllItems();
  }

  @Override public ArrayList<AccessKey> getAllAccessKey()
      throws RemoteException, SQLException
  {
    return cafePersistence.getAllAccessKey();
  }

  @Override public ItemList getItemsByType(String type)
      throws RemoteException, SQLException
  {
    return null;
  }

  @Override public int receiveOrder(Order order) throws RemoteException
  {
    int id = cafePersistence.receiveOrder(order);
    property.firePropertyChange("pending", null, null);
    return id;
  }

  @Override public void completeOrder(Order order) throws RemoteException
  {
    cafePersistence.completeOrder(order.getId());
    property.firePropertyChange("completed", null, null);
  }

  @Override public void cancelOrder(Order order) throws RemoteException
  {
    cafePersistence.cancelOrder(order.getId());
  }

  @Override public void editCommentInOrder(Order order, String comment)
      throws RemoteException
  {
    cafePersistence.editComment(order.getId(),comment);
  }



  @Override public void receiveUnpaidOrder(Order order) throws RemoteException
  {
    cafePersistence.receiveOrder(order);
  }

  @Override public void acceptPayment(Order order) throws RemoteException
  {
    cafePersistence.acceptPayment(order.getId());
  }

  @Override public void addItemToProductList(Item item)
  {
    cafePersistence.addItemToProductList(item);
  }

  @Override public void addAccessKey(AccessKey accessKey) throws RemoteException
  {
    cafePersistence.addAccessKey(accessKey);
  }

  @Override public ArrayList<Order> getAllPendingOrders() throws RemoteException
  {
    return cafePersistence.getOrdersByStatus("pending");
  }

  @Override public ArrayList<Order> getAllUnpaidOrders() throws RemoteException
  {
    return cafePersistence.getOrdersByStatus("unpaid");
  }
   @Override public ArrayList<Order> getAllCompletedOrders() throws RemoteException
   {
    return cafePersistence.getOrdersByStatus("completed");
   }

  @Override public void removeItemFromProductList(Item item)
      throws RemoteException, SQLException
  {
    cafePersistence.removeItemFromProductList(item);
  }

  @Override public ArrayList<Extra> getAllExtrasByType(String type)
      throws RemoteException, SQLException
  {
    return cafePersistence.getAllExtrasByType(type);
  }
  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames) throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener, propertyNames);
  }

  @Override public void removeAccessKey(AccessKey accessKey)
      throws RemoteException, SQLException
  {
    cafePersistence.removeAccessKey(accessKey);
  }
  @Override public String getUserType(String pwd)
      throws RemoteException, SQLException
  {
    System.out.println("---SERVER---");
    System.out.println("Server|From client: " + pwd);

    String ak = cafePersistence.getUserType(pwd);
    System.out.println("Server|From db: " + ak);
    return ak;
  }

  @Override public ArrayList<Extra> getAllExtras() throws SQLException
  {
    return cafePersistence.getAllExtras();
  }

  @Override public void addExtraToExtraList(Extra extra)
      throws RemoteException, SQLException
  {
    cafePersistence.addExtraToExtraList(extra);
  }

  @Override public void removeExtraFromExtraList(Extra extra)
      throws RemoteException, SQLException
  {
    cafePersistence.removeExtraFromExtraList(extra);
  }
}
