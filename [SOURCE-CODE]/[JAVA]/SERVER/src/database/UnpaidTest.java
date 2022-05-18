package database;

import utility.Item;
import utility.Order;

import java.sql.SQLException;

public class UnpaidTest
{
  public static void main(String[] args) throws SQLException
  {
    Order order = new Order(true);
    Item coffee = new Item(1, "Coffee", "coffee", 20, ":)");
    order.addItem(coffee);
    ConcreteOrderDAO.getInstance().create(order);
    ConcreteOrderDAO.getInstance().create(order);
    ConcreteOrderDAO.getInstance().create(order);
  }
}
