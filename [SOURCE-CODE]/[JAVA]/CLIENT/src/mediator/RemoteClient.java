package mediator;

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

  public RemoteClient() throws MalformedURLException, NotBoundException, RemoteException
  {
    server = (RemoteCafeServer) Naming.lookup("rmi://localhost:1099/Cafe");
    //UnicastRemoteObject.exportObject(this,0);
    //This will be needed when we will be doing the RMI Observer
  }

  public ItemList getAllItems() throws RemoteException, SQLException
  {
    return server.getAllItems();
  }

  public void receiveOrder(Order order) throws RemoteException
  {
    System.out.println("Client: Send the order to the server");
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

  public ArrayList<Order> getAllPendingOrders() throws RemoteException
  {
    return server.getAllPendingOrders();
  }

  public void removeItemFromProductList(Item item) throws RemoteException
  {
    server.removeItemFromProductList(item);
  }
}
