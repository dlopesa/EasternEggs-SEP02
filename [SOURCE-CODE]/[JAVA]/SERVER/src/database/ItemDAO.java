package database;

import utility.Item;

public interface ItemDAO
{
  public void createItem(Item item);
  public void readItemById(int id);
  public void updateItem(int id, Item item);
  public void deleteItem(int id);
}
