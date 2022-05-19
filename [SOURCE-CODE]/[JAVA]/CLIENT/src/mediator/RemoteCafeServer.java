package mediator;

import utility.Extra;
import utility.Item;
import utility.ItemList;
import utility.Order;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteCafeServer extends Remote
{
  ItemList getAllItems() throws RemoteException, SQLException;
  ItemList getItemsByType(String type) throws RemoteException, SQLException;
  void receiveOrder(Order order) throws RemoteException;
  void completeOrder(Order order) throws RemoteException;
  void cancelOrder(Order order) throws RemoteException;
  void editCommentInOrder(Order order, String comment) throws RemoteException;
  void receiveUnpaidOrder(Order order) throws RemoteException;
  void acceptPayment(Order order) throws RemoteException;
  void addItemToProductList(Item item) throws RemoteException;
  ArrayList<Order> getAllPendingOrders() throws RemoteException;
  ArrayList<Order> getAllUnpaidOrders() throws RemoteException;
  void removeItemFromProductList(Item item) throws RemoteException;
  ArrayList<Extra> getAllExtrasByType(String type) throws RemoteException;
}
