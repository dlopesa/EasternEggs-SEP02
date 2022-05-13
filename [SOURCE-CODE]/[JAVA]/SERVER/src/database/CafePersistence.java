package database;

import utility.Item;
import utility.ItemList;
import utility.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CafePersistence
{
  public ItemList getAllItems() throws SQLException;
  public ItemList getItemsByType();
  public void receiveOrder(Order order);
  public void completeOrder(int orderId);
  public void acceptPayment(int orderId);
  public void editComment(int orderId, String comment);
  public void addItemToProductList(Item item);
  public ArrayList<Order> getOrdersByStatus(String status);
}
