package model;

import utility.Extra;
import utility.Item;
import utility.ItemList;
import utility.Order;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Model
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
  void cancelOrder();
  void payForOrder(boolean isCash);
  void setIsTakeAway(boolean isTakeAway);
  void addItemToProductList(Item item);
  void removeItemFromProductList(Item item);
  ArrayList<Order> getAllPendingOrders();
  void completeOrder(Order order) throws RemoteException;
  void acceptOrder(Order order);
  void editOrderComment(Order order);
  void cancelUnpaidOrder(Order order);
  ArrayList<String> getAllTypes();
}
