package database;

import utility.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO
{
  public void createItem(Item item) throws SQLException;
  public void readItemById(int id);
  public void updateItem(int id, Item item);
  public void deleteItem(int id);
  public ArrayList<Item> getAllItems() throws SQLException;
}
