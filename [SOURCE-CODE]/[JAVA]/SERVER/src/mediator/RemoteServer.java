package mediator;

import database.CafeDatabase;
import database.CafePersistence;
import database.ConcreteOrderDAO;
import database.OrderDAO;
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

  @Override public ItemList getItemsByType(String type)
      throws RemoteException, SQLException
  {
    return null;
  }

  @Override public void receiveOrder(Order order) throws RemoteException
  {
    cafePersistence.receiveOrder(order);
    //Shouldn't we call the CafePersistence instead?
    //CafeDatabase get instance method is not use.
    property.firePropertyChange("pending", null, null);
  }

  @Override public void completeOrder(Order order) throws RemoteException
  {
    cafePersistence.completeOrder(order.getId());
    property.firePropertyChange("completed", null, null);
  }

  @Override public void receiveUnpaidOrder(Order order) throws RemoteException
  {

  }

  @Override public void acceptPayment(Order order) throws RemoteException
  {
    //receiveOrder(order);
  }

  @Override public void addItemToProductList(Item item)
  {
    cafePersistence.addItemToProductList(item);
  }

  @Override public ArrayList<Order> getAllPendingOrders() throws RemoteException
  {
    return cafePersistence.getOrdersByStatus("pending");
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
}
