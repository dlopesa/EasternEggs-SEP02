package database;

import utility.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConcreteOrderDAO implements OrderDAO
{
  private static ConcreteOrderDAO instance;
  private static Object lock = new Object();

  private ConcreteOrderDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static ConcreteOrderDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new ConcreteOrderDAO();
        }
      }
    }
    return instance;
  }

  @Override public void create(Order order) throws SQLException
  {
    String comment = order.getComment();
    LocalDateTime dateTime = order.getDateTime().getLocalDateTime();
    double price = order.getPrice();
    String status = order.getStatus();
    ArrayList<Item> items = order.getItemList().getAllItems();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO order_(comment, datetime, price, status) VALUES (?,?,?,?);",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, comment);
      statement.setTimestamp(2, Timestamp.valueOf(dateTime));
      statement.setDouble(3, price);
      statement.setString(4, status);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      int orderId = 0;
      if (keys.next())
      {
        orderId = keys.getInt(1);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
      for (Item item : items)
      {
        int itemId = item.getId();
        ArrayList<Extra> extras = item.getExtras();
        PreparedStatement itemInOrderStatement = connection.prepareStatement("INSERT INTO iteminorder(order_id, item_id) VALUES (?,?)");
        itemInOrderStatement.setInt(1, orderId);
        itemInOrderStatement.setInt(2, itemId);
        itemInOrderStatement.executeUpdate();
        for (Extra extra : extras)
        {
          System.out.println("entered statement.");
          PreparedStatement extrainiteminorderStatement = connection.prepareStatement(
              "INSERT INTO extrainiteminorder(extra_id, item_id, order_id) VALUES (?,?,?)");
          extrainiteminorderStatement.setString(1, extra.getName());
          extrainiteminorderStatement.setInt(2, itemId);
          extrainiteminorderStatement.setInt(3, orderId);
          extrainiteminorderStatement.executeUpdate();
        }
      }

    }
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=cafe", "postgres", "1234");
  }

  @Override public Order readById(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM order_ WHERE order_id = ?");
      statement.setInt(1, id);
      ResultSet orderResultSet = statement.executeQuery();
      if (orderResultSet.next())
      {
        String comment = orderResultSet.getString("comment");
        DateTime dateTime = new DateTime(orderResultSet.getTimestamp("datetime").toLocalDateTime());
        double price = 0;
        String status = orderResultSet.getString("status");

        Order order = new Order(id, new ItemList(), comment, dateTime, price, status);

        PreparedStatement findItemsInOrder = connection.prepareStatement("SELECT * FROM iteminorder WHERE order_id = ?");
        findItemsInOrder.setInt(1, id);
        ResultSet itemInOrderResultSet = findItemsInOrder.executeQuery();
        while (itemInOrderResultSet.next())
        {
          int itemId = itemInOrderResultSet.getInt("item_id");
          PreparedStatement findActualItem = connection.prepareStatement("SELECT * FROM item WHERE item_id = ?");
          findActualItem.setInt(1, itemId);
          ResultSet itemResultSet = findActualItem.executeQuery();
          if (itemResultSet.next())
          {
            String itemName = itemResultSet.getString("name");
            String itemType = itemResultSet.getString("type");
            double itemPrice = itemResultSet.getDouble("price");
            String description = itemResultSet.getString("description");

            Item item = new Item(itemId, itemName, itemType, itemPrice, description);

            order.addItem(item);

            PreparedStatement findExtrasInItem = connection.prepareStatement("SELECT * FROM extrainiteminorder WHERE (order_id = ? AND item_id = ?)");
            findExtrasInItem.setInt(1, id);
            findExtrasInItem.setInt(2, itemId);

            ResultSet extrasInItem = findExtrasInItem.executeQuery();

            while (extrasInItem.next())
            {
              String extraName = extrasInItem.getString("extra_id");
              Extra extra = new Extra(extraName);
              order.addExtraToItem(item, extra);
            }
          }
        }
        return order;
      }
    }
    System.out.println("No order was found with this id?");
    return null;
  }

  @Override public void updateStatus(int orderId, String status) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement findOrder = connection.prepareStatement("SELECT * FROM order_ WHERE order_id = ?");
      findOrder.setInt(1, orderId);
      ResultSet orderResultSet = findOrder.executeQuery();
      if (orderResultSet.next())
      {
        PreparedStatement updateStatus = connection.prepareStatement("UPDATE order_ SET status = ? WHERE order_id = ?");
        updateStatus.setString(1, status);
        updateStatus.setInt(2, orderId);
        updateStatus.executeUpdate();
      }
    }
  }

  @Override public void updateComment(int orderId, String comment) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement findOrder = connection.prepareStatement("SELECT * FROM order_ WHERE order_id = ?");
      findOrder.setInt(1, orderId);
      ResultSet orderResultSet = findOrder.executeQuery();
      if (orderResultSet.next())
      {
        PreparedStatement updateStatus = connection.prepareStatement("UPDATE order_ SET comment = ? WHERE order_id = ?");
        updateStatus.setString(1, comment);
        updateStatus.setInt(2, orderId);
        updateStatus.executeUpdate();
      }
    }
  }

  @Override public void addItemToOrder(int orderId, Item item) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement addItemToOrderStatement = connection.prepareStatement("INSERT INTO iteminorder(item_id, order_id) VALUES (?, ?)");
      addItemToOrderStatement.setInt(1, item.getId());
      addItemToOrderStatement.setInt(2, orderId);
      addItemToOrderStatement.executeUpdate();
    }
  }

  @Override public void delete(int orderId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM order_ WHERE order_id = ?");
      deleteStatement.setInt(1, orderId);
      deleteStatement.executeUpdate();
    }
  }

  @Override public ArrayList<Order> getOrdersByStatus(String status)
      throws SQLException
  {
    ArrayList<Order> returnOrders = new ArrayList<>();
    ArrayList<Integer> returnOrdersIds = new ArrayList<>();
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM order_ WHERE status = ?");
      statement.setString(1,status);
      ResultSet orderResultSet = statement.executeQuery();
      while (orderResultSet.next()) {
        int id = orderResultSet.getInt("order_id");
        returnOrdersIds.add(id);
      }

      for (int i : returnOrdersIds) {
        returnOrders.add(readById(i));
      }
    }
    return returnOrders;
  }
}
