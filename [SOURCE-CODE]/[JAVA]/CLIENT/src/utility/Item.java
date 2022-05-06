package utility;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable
{
  private int id;
  private String name;
  private String type;
  private double price;
  private String description;
  private ArrayList<Extra> extras;

  public ArrayList<Extra> getExtras()
  {
    return extras;
  }

  public Item(int id, String name, String type, double price,
      String description)
  {
    this.id = id;
    this.name = name;
    this.type = type;
    this.price = price;
    this.description = description;
    this.extras = new ArrayList<>();
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public Item(int id, String name, String type, double price)
  {
    this.id = id;
    this.name = name;
    this.type = type;
    this.price = price;
    this.description = "";
    this.extras = new ArrayList<>();
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
    return "Item{" + "id=" + id + ", name='" + name + '\'' + ", type='" + type
        + '\'' + ", price=" + price + ", description='" + description + '\''
        + ", extras=" + extras + '}';
  }

  public Item copy()
  {
    return new Item(id, name, type, price, description);
  }
}
