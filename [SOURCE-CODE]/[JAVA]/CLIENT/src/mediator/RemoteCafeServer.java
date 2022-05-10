package mediator;

import utility.Item;
import utility.ItemList;
import utility.Order;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCafeServer extends Remote
{
  ItemList getAllItems() throws RemoteException;
  ItemList getItemsByType(String type) throws RemoteException;
  void receiveOrder(Order order) throws RemoteException;
  void completeOrder(Order order) throws RemoteException;
  void receiveUnpaidOrder(Order order) throws RemoteException;
  void acceptPayment(Order order) throws RemoteException;
  void addItemToProductList(Item item) throws RemoteException;
}
