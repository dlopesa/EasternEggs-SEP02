package mediator;

import utility.Item;
import utility.ItemList;
import utility.Order;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteCafeServer extends Remote, RemoteSubject<String, String>
{
  ItemList getAllItems() throws RemoteException, SQLException;
  ItemList getItemsByType(String type) throws RemoteException, SQLException;
  void receiveOrder(Order order) throws RemoteException;
  void completeOrder(Order order) throws RemoteException;
  void receiveUnpaidOrder(Order order) throws RemoteException;
  void acceptPayment(Order order) throws RemoteException;
  void addItemToProductList(Item item) throws RemoteException;
  ArrayList<Order> getAllPendingOrders() throws RemoteException;
  ArrayList<Order> getAllCompletedOrders() throws RemoteException;
  void removeItemFromProductList(Item item) throws RemoteException, SQLException;
  boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames) throws RemoteException;
  boolean removeListener(GeneralListener<String, String> listener,
      String... propertyNames) throws RemoteException;
}
