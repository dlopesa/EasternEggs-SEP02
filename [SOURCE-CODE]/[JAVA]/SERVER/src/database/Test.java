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
    Item item1 = new Item(1, "Coffee","coffee",20,":)");
    Item item2 = new Item(2,"Tea","tea",7,"chilly tea");
    Extra caramelsyrup = new Extra("caramel syrup");

    order2.addItem(item1);
    order2.addExtraToItem(item1,caramelsyrup);

    ConcreteOrderDAO.getInstance().updateStatus(9, "completed");
    ConcreteOrderDAO.getInstance().updateComment(9,"yayyy");

  }
}
