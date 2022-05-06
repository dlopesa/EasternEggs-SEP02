package database;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import utility.Extra;
import utility.Item;
import utility.Order;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
class ConcreteOrderDAOTest
{

  private Order order;
  private Item coffee;
  private Item tea;
  private Extra honey;



  @BeforeEach void setUp()
  {
    order = new Order(false);
    coffee = new Item(1, "Coffee", "coffee", 20, ":)");
    tea = new Item(2, "Tea", "tea", 7, "lovely herbal tea");
    honey = new Extra("honey");

    order.addItem(coffee);
    order.addItem(tea);
    order.addExtraToItem(tea, honey);
  }

  @AfterEach void tearDown() throws SQLException
  {
  }

  @Test void AgetInstance() throws SQLException
  {
    assertNotNull(ConcreteOrderDAO.getInstance());
  }

  @Test void Bcreate1() throws SQLException // 1
  {
    assertDoesNotThrow(() -> {
      ConcreteOrderDAO.getInstance().create(order);
    });
  }

  @Test  void Ccreate2() throws SQLException // 2
  {
    ConcreteOrderDAO.getInstance().create(order);
    Order createdOrder = ConcreteOrderDAO.getInstance().readById(2);

    System.out.println("Order: " + order);
    System.out.println("Created order: " + createdOrder);

    // I struggled with this for a long time. To see if two Objects are
    // the same, the method kept comparing their references even though
    // their contents were the same, so the only way to see if they
    // were equal was to Equal their toString(), which looks only at
    // the contents.
    assertEquals(order.toString(), createdOrder.toString());
  }

  @Test  void DreadById1() throws SQLException
  {
    assertDoesNotThrow(() -> ConcreteOrderDAO.getInstance().readById(1));
  }

  @Test  void EreadById2() throws SQLException
  {
    assertNull(ConcreteOrderDAO.getInstance().readById(2000));
  }

  @Test  void FupdateStatus0() throws SQLException
  {
    assertDoesNotThrow(
        () -> ConcreteOrderDAO.getInstance().updateStatus(1, "completed"));
  }

  @Test  void GupdateStatus1()
      throws SQLException // 3
  {
    ConcreteOrderDAO.getInstance().create(order);
    ConcreteOrderDAO.getInstance().updateStatus(3, "completed");

    Order orderUpdated = ConcreteOrderDAO.getInstance().readById(3);

    assertEquals("completed", orderUpdated.getStatus());
  }

  @Test  void HupdateStatus2()
      throws SQLException // 4
  {
    ConcreteOrderDAO.getInstance().create(order);
    ConcreteOrderDAO.getInstance().updateStatus(4, "pending");

    Order orderUpdated = ConcreteOrderDAO.getInstance().readById(4);

    assertEquals("pending", orderUpdated.getStatus());
  }

  @Test  void IupdateStatus3()
      throws SQLException // 5
  {
    ConcreteOrderDAO.getInstance().create(order);
    ConcreteOrderDAO.getInstance().updateStatus(5, "unpaid");

    Order orderUpdated = ConcreteOrderDAO.getInstance().readById(5);

    assertEquals("unpaid", orderUpdated.getStatus());
  }

  @Test  void JupdateStatusE()
      throws SQLException // 6
  {
    ConcreteOrderDAO.getInstance().create(order);

    assertThrows(SQLException.class, () -> {
      ConcreteOrderDAO.getInstance().updateStatus(6, "blabla");
    });
  }

  @Test  void KupdateComment()
      throws SQLException // 7
  {
    ConcreteOrderDAO.getInstance().create(order);
    assertDoesNotThrow(
        () -> ConcreteOrderDAO.getInstance().updateComment(7, "happy"));
  }

  @Test void LaddItemToOrder1()
      throws SQLException // 8
  {
    order.removeItem(coffee);
    ConcreteOrderDAO.getInstance().create(order);
    assertDoesNotThrow(
        () -> ConcreteOrderDAO.getInstance().addItemToOrder(8, coffee));
  }

  @Test  void MaddItemToOrder2()
      throws SQLException
  {
    assertThrows(SQLException.class, () -> {
      ConcreteOrderDAO.getInstance().addItemToOrder(2000, coffee);
    });
  }

  @Test void NaddItemToOrder3()
      throws SQLException // 9
  {
    order.removeItem(coffee);
    ConcreteOrderDAO.getInstance().create(order);
    ConcreteOrderDAO.getInstance().addItemToOrder(9,coffee);
    Order orderObject = ConcreteOrderDAO.getInstance().readById(9);
    Item item = orderObject.getItemList().getAllItems().get(1);
    assertEquals(coffee, item);
  }

  @Test  void Odelete1() throws SQLException // 10
  {
    ConcreteOrderDAO.getInstance().create(order);
    assertDoesNotThrow(() -> ConcreteOrderDAO.getInstance().delete(10));
  }

  @Test  void Pdelete2() throws SQLException
  {
    assertDoesNotThrow(() -> {
      ConcreteOrderDAO.getInstance().delete(2000);
    });
  }

  @Test  void Qdelete3() throws SQLException // 11
  {
    ConcreteOrderDAO.getInstance().create(order);
    ConcreteOrderDAO.getInstance().delete(11);
    assertNull(ConcreteOrderDAO.getInstance().readById(11));
  }
}