package database;

import utility.Item;
import utility.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO
{
  public int create(Order order) throws SQLException;
  public Order readById(int id) throws SQLException;
  public void updateStatus(int orderId, String status) throws SQLException;
  public void updateComment(int orderId, String comment) throws SQLException;
  public void addItemToOrder(int orderId, Item item) throws SQLException;
  public void delete(int orderId) throws SQLException;
  public ArrayList<Order> getOrdersByStatus(String status) throws SQLException;
}
