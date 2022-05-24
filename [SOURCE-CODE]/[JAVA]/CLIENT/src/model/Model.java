package model;

import utility.*;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Model extends PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  void setUserType(String type);
  String getUserType(String pwd);
  void addItemToOrder(Item item) throws IllegalAccessException;
  int submitOrder() throws NullPointerException, IllegalAccessException;
  void addExtraToItem(Extra extra, Item item) throws IllegalAccessException;
  void removeExtraFromItem(Extra extra, Item item)
      throws IllegalAccessException;
  void removeAccessKey(AccessKey accessKey) throws IllegalAccessException;
  void removeItemFromOrder(Item item) throws IllegalAccessException;
  Order getOrder() throws IllegalAccessException;
  ItemList getAllExistingItems()
      throws RemoteException, SQLException, IllegalAccessException;
  ArrayList<AccessKey> getAllAccessKey()
      throws RemoteException, SQLException, IllegalAccessException;
  void quitAndCancelOrder() throws IllegalAccessException;
  int payForOrder(boolean isCash) throws IllegalAccessException;
  void setIsTakeAway() throws IllegalAccessException;
  void addItemToProductList(Item item) throws IllegalAccessException;
  void addAccessKey(AccessKey accessKey)
      throws SQLException, RemoteException, IllegalAccessException;
  void removeItemFromProductList(Item item) throws IllegalAccessException;
  ArrayList<Order> getAllPendingOrders() throws IllegalAccessException;
  ArrayList<Order> getAllUnpaidOrders() throws IllegalAccessException;
  ArrayList<Order> getAllCompletedOrders() throws IllegalAccessException;
  void completeOrder(Order order) throws RemoteException,
      IllegalAccessException;
  void acceptPayment(Order order) throws IllegalAccessException;
  void editOrderCommentByCashier(Order order, String comment)
      throws IllegalAccessException;
  void editOrderCommentByCustomer(String comment) throws IllegalAccessException;
  void cancelUnpaidOrder(Order order) throws IllegalAccessException;
  ArrayList<String> getAllTypes() throws IllegalAccessException;
  ArrayList<Extra> getAllExtrasByType(String type)
      throws IllegalAccessException;
  ArrayList<String> getAllPermissions() throws IllegalAccessException;
  ArrayList<Extra> getAllExtras() throws IllegalAccessException;
  void addExtraToExtraList(Extra extra) throws IllegalAccessException;
  void removeExtraFromExtraList(Extra extra) throws IllegalAccessException;
}
