package utility;

import jdk.swing.interop.SwingInterOpUtils;
import org.postgresql.util.OSUtil;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemList implements Serializable
{
  private ArrayList<Item> items;

  public ItemList() {
    this.items = new ArrayList<>();
  }

  public void add(Item item) {
    items.add(item.copy());
  }

  public void remove(Item item) {
    items.remove(item);
  }

  public void addExtraToItem(Item item, Extra extra) {
    boolean found = false;
    for (Item itemInList : items) {
      if (itemInList.getId() == item.getId()) {
        itemInList.addExtra(extra);
        found = true;
        break;
      }
    }
    if (!found) {
      System.out.println("item not found: " + item.toString() + ", extra was: " + extra);
    }
  }

  public ArrayList<Item> getAllItems() {
    return new ArrayList<>(items);
  }

  public ArrayList<Item> getItemsByType(String type) {
    ArrayList<Item> itemsOfThisType = new ArrayList<>();
    for (Item item : items) {
      if (item.getType().equals(type)) {
        itemsOfThisType.add(item);
      }
    }
    return itemsOfThisType;
  }

  public ItemList copy() {
    ItemList copy = new ItemList();
    for (Item item : items) {
      copy.add(item);
    }
    return copy;
  }

  @Override public String toString()
  {
    return "ItemList{" + "items=" + items + '}';
  }
}
