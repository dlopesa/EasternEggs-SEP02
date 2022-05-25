package utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class that represent an item list
 */
public class ItemList implements Serializable
{
  private ArrayList<Item> items;

  /**
   * A zero argument constructor
   */
  public ItemList() {
    this.items = new ArrayList<>();
  }


  public void setItems(ArrayList<Item> items) {
    this.items = items;
  }

  /**
   * Method that adds an item to a list
   * @param item
   */
  public void add(Item item) {
    items.add(item);
  }

  /**
   * Method that removes an item from a list
   * @param item
   */
  public void remove(Item item) {
    if(contains(item))
    {
      items.remove(item);
    }
    else
    {
      throw new IllegalStateException("NOT REMOVED");
    }
  }

  /**
   * Adds an extra to an item
   * @param item
   * @param extra
   */
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
      System.out.println("Item not found: " + item.toString() + ", extra was: " + extra);
    }
  }

  /**
   * Gets all the items in a list? TODO ask someone
   * @return
   */
  public ArrayList<Item> getAllItems() {
    return items;
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

  /**
   * Compare two item lists and check if them are equal
   * @param o
   * @returns a bollean comparing two item lists and check if them are equal
   */
  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ItemList itemList = (ItemList) o;
    return items.equals(itemList.items);
  }

  @Override public int hashCode()
  {
    return Objects.hash(items);
  }

  /**
   *
   * @returns a String with all the information of a item list
   */
  @Override public String toString()
  {
    return "ItemList{" + "items=" + items + '}';
  }

  /**
   * Method that checks if an Item is on a list
   * @param item
   * @return
   */
  public boolean contains(Item item)
  {
    return items.contains(item);
  }
}
