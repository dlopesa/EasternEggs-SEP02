package database;

import utility.Extra;
import utility.Item;
import utility.Order;

import java.sql.SQLException;

public class Test
{
  public static void main(String[] args) throws SQLException
  {
    Order order1 = new Order(false);
    Order order2 = new Order(false);
    Order order3 = new Order(false);
    order1.setComment("aaaaa");
    order2.setComment("blubla");
    order3.setComment("sheep");
    Item item1 = new Item(1, "Coffee", "coffee", 20, ":)");
    Item item2 = new Item(2,"Tea", "tea", 7, "chilly tea");

    order1.addItem(item1);
    order2.addItem(item2);
    order3.addItem(item1);
    order3.addItem(item2);

    ConcreteOrderDAO.getInstance().create(order1);
    ConcreteOrderDAO.getInstance().create(order2);
    ConcreteOrderDAO.getInstance().create(order3);

  }
}
