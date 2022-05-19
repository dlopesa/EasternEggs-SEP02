package mediator;

import utility.AccessKey;
import utility.Item;
import utility.ItemList;
import utility.Order;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RemoteClient
{
  private RemoteCafeServer server;

  public RemoteClient()
      throws MalformedURLException, NotBoundException, RemoteException
  {
    server = (RemoteCafeServer) Naming.lookup("rmi://localhost:1099/Cafe");
    //UnicastRemoteObject.exportObject(this,0);
    //This will be needed when we will be doing the RMI Observer
  }

  public ItemList getAllItems() throws RemoteException, SQLException
  {
    return server.getAllItems();
  }

  public ArrayList<AccessKey> getAllAccessKey()
      throws RemoteException, SQLException
  {
    return server.getAllAccessKey();
  }

  public void receiveOrder(Order order) throws RemoteException
  {
    server.receiveOrder(order);
  }

  public void completeOrder(Order order) throws RemoteException
  {
    server.completeOrder(order);
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

  public void removeItemFromProductList(Item item) throws RemoteException
  {
    server.removeItemFromProductList(item);
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
