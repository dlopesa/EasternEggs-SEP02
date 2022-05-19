package model;

import utility.Extra;
import utility.Item;
import utility.ItemList;
import utility.Order;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Model extends PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  void setUserType(String type);
  String getUserType();
  void addItemToOrder(Item item);
  void submitOrder()throws NullPointerException;
  void addExtraToItem(Extra extra, Item item);
  void removeExtraFromItem(Extra extra, Item item);
  void removeItemFromOrder(Item item);
  Order getOrder();
  ItemList getAllExistingItems() throws RemoteException, SQLException;
  void setComment(String comment);
  void quitAndCancelOrder();
  void payForOrder(boolean isCash);
  void setIsTakeAway(boolean isTakeAway);
  void addItemToProductList(Item item);
  void removeItemFromProductList(Item item);
  ArrayList<Order> getAllPendingOrders();
  ArrayList<Order> getAllUnpaidOrders();
  void completeOrder(Order order) throws RemoteException;
  void acceptPayment(Order order);
  void editOrderComment(Order order, String comment);
  void cancelUnpaidOrder(Order order);
  ArrayList<String> getAllTypes();
  ArrayList<Extra> getAllExtrasByType(String type);
}
