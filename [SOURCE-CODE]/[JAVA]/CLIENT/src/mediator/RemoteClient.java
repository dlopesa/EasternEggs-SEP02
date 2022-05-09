package mediator;

import utility.Item;
import utility.ItemList;
import utility.Order;

import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteClient
{
  private RemoteCafeServer server;

  public RemoteClient()
      throws MalformedURLException, NotBoundException, RemoteException
  {
    server = (RemoteCafeServer)
        Naming.lookup("rmi://localhost:1099/Cafe");
    //UnicastRemoteObject.exportObject(this,0);
    //This will be needed when we will be doing the RMI Observer
  }

  public ItemList getAllItems() throws RemoteException
  {
    return server.getAllItems();
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

  public void addItemToProductList(Item item)
  {
    server.addItemToProductList(item);
  }

}
