package model;

import utility.Extra;
import utility.Item;
import utility.ItemList;
import utility.Order;

import java.util.ArrayList;

public interface Model
{
  void setUserType(String type);
  String getUserType();
  void addItemToOrder(Item item);
  void submitOrder();
  void addExtraToItem(Extra extra, Item item);
  void removeExtraFromItem(Extra extra, Item item);
  void removeItemFromOrder(Item item);
  Order getOrder();
  ItemList getAllExistingItems();
  void setComment(String comment);
  void cancelOrder();
  void payForOrder(boolean isCash);
  void setIsTakeAway(boolean isTakeAway);
  void addItemToProductList(Item item);
  void removeItemFromProductList(Item item);
  ArrayList<Order> getAllPendingOrders();
  void completeOrder(Order order);
  void acceptOrder(Order order);
  void editOrderComment(Order order);
  void cancelUnpaidOrder(Order order);

}
