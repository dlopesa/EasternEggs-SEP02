package database;

import utility.Extra;
import utility.Item;
import utility.ItemList;
import utility.Order;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CafePersistence
{
  public ItemList getAllItems() throws SQLException;
  public ItemList getItemsByType(String type);
  public int receiveOrder(Order order);
  public void completeOrder(int orderId);
  public void acceptPayment(int orderId);
  public void cancelOrder(int orderId);
  public void editComment(int orderId, String comment);
  public void addItemToProductList(Item item);
  public ArrayList<Order> getOrdersByStatus(String status);
  public void removeItemFromProductList(Item item) throws SQLException;
  ArrayList<Extra> getAllExtrasByType(String type) throws SQLException;
}
