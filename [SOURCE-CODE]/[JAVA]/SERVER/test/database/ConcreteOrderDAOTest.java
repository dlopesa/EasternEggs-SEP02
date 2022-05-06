package database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.Extra;
import utility.Item;
import utility.Order;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteOrderDAOTest
{

  @BeforeEach void setUp()
  {
    Order order = new Order(false);
    Item coffee = new Item(1,"Coffee","coffee",10,":)");
    Item tea = new Item(1,"Tea","tea",7,"lovely herbal tea");
    Extra honey = new Extra("caramel syrup");

    order.addItem(coffee);
    order.addItem(tea);
    order.addExtraToItem(tea,honey);
  }

  @AfterEach void tearDown()
  {
  }

  @Test void getInstance() throws SQLException
  {
    assertEquals(new ConcreteOrderDAO(), ConcreteOrderDAO.getInstance());
  }

  @Test void create()
  {
    
  }

  @Test void readById()
  {
  }

  @Test void updateStatus()
  {
  }

  @Test void updateComment()
  {
  }

  @Test void addItemToOrder()
  {
  }
}