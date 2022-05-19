package database;

import utility.Extra;
import utility.AccessKey;
import utility.Item;
import utility.ItemList;
import utility.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CafePersistence
{
  public ItemList getAllItems() throws SQLException;
  public ItemList getItemsByType(String type);
  public ArrayList<AccessKey> getAllAccessKey() throws SQLException;
  public int receiveOrder(Order order);
  public void completeOrder(int orderId);
  public void acceptPayment(int orderId);
  public void cancelOrder(int orderId);
  public void editComment(int orderId, String comment);
  public void addItemToProductList(Item item);
  public void addAccessKey(AccessKey accessKey);
  public ArrayList<Order> getOrdersByStatus(String status);
  public void removeItemFromProductList(Item item) throws SQLException;
  ArrayList<Extra> getAllExtrasByType(String type) throws SQLException;
  public void removeAccessKey(AccessKey accessKey) throws SQLException;
  public String getUserType(String pwd) throws SQLException;
}
