package utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Item implements Serializable
{
  private int id;
  private int item_in_order_id;
  private String name;
  private String type;
  private double price;
  private String description;
  private ArrayList<Extra> extras;

  public ArrayList<Extra> getExtras()
  {
    return extras;
  }

  public Item(int id, String name, String type, double price, String description)
  {
    this.id = id;
    this.name = name;
    this.type = type;
    this.price = price;
    this.description = description;
    this.extras = new ArrayList<>();
    this.item_in_order_id=-1;
  }

  public Item(String name, String type, double price, String description)
  {
    this.id = -1;
    this.name = name;
    this.type = type;
    this.price = price;
    this.description = description;
    this.extras = new ArrayList<>();
    this.item_in_order_id=-1;
  }

  public Item(int id, String name, String type, double price)
  {
    this.id = id;
    this.name = name;
    this.type = type;
    this.price = price;
    this.description = "";
    this.extras = new ArrayList<>();
    this.item_in_order_id=-1;
  }



  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getItem_in_order_id()
  {
    return item_in_order_id;
  }

  public void setItem_in_order_id(int id)
  {
    this.item_in_order_id=id;
  }

  public void addExtra(Extra extra)
  {
    extras.add(extra);
  }

  public void removeExtra(Extra extra)
  {
    extras.remove(extra);
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public double getPrice()
  {
    return price;
  }

  public void setPrice(double price)
  {
    this.price = price;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  @Override public String toString()
  {
    return "Item{" + "id=" + id + ", name='" + name + '\'' + ", type='" + type + '\'' + ", price="
        + price + ", description='" + description + '\'' + ", extras=" + extras + '}';
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Item item = (Item) o;
    return id == item.id && Double.compare(item.price, price) == 0 && Objects.equals(name,
        item.name) && Objects.equals(type, item.type) && Objects.equals(description,
        item.description);
  }

  @Override public int hashCode()
  {
    return Objects.hash(id, name, type, price, description, extras);
  }

}
