package model;

import utility.*;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Model extends PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  void setUserType(String type);
  String getUserType(String pwd);
  void addItemToOrder(Item item);
  int submitOrder()throws NullPointerException;
  void addExtraToItem(Extra extra, Item item);
  void removeExtraFromItem(Extra extra, Item item);
  void removeAccessKey(AccessKey accessKey);
  void removeItemFromOrder(Item item);
  Order getOrder();
  ItemList getAllExistingItems() throws RemoteException, SQLException;
  ArrayList<AccessKey> getAllAccessKey() throws RemoteException, SQLException;
  void setComment(String comment);
  void quitAndCancelOrder();
  int payForOrder(boolean isCash);
  void setIsTakeAway();
  void addItemToProductList(Item item);
  void addAccessKey(AccessKey accessKey) throws SQLException;
  void removeItemFromProductList(Item item);
  ArrayList<Order> getAllPendingOrders();
  ArrayList<Order> getAllUnpaidOrders();
  ArrayList<Order> getAllCompletedOrders();
  void completeOrder(Order order) throws RemoteException;
  void acceptPayment(Order order);
  void editOrderComment(Order order, String comment);
  void cancelUnpaidOrder(Order order);
  ArrayList<String> getAllTypes();
  ArrayList<Extra> getAllExtrasByType(String type);
  ArrayList<String> getAllPermissions();
}
