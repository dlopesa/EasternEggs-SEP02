package database;

import utility.ItemList;
import utility.Order;

public interface CafePersistence
{
  public ItemList getAllItems();
  public ItemList getItemsByType();
  public void receiveOrder(Order order);
  public void completeOrder(int orderId);
  public void acceptPayment(int orderId);
  public void editComment(int orderId, String comment);
}
