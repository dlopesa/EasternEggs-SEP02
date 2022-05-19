package utility;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable, UnnamedPropertyChangeSubject
{
  private int id; // NEW INSTANCE VARIABLE. ONLY USEFUL FOR WHEN THE ORDER COMES
  // FROM THE DATABASE.
  private ItemList itemList;
  private String comment;
  private DateTime dateTime;
  private double price;
  private String status;
  private PropertyChangeSupport property;

  public Order(boolean paidWithCash)
  {
    property = new PropertyChangeSupport(this);
    id = -1;
    itemList = new ItemList();
    comment = "";
    dateTime = new DateTime();
    price = 0;
    if (paidWithCash)
    {
      status = "unpaid";
    }
    else
    {
      status = "pending";
    }
  }

  public Order(int id, ItemList itemList, String comment, DateTime dateTime, double price,
      String status) // NEW CONSTRUCTOR FOR WHEN YOU GET AN ORDER
  // FROM THE DATABASE.
  {
    property = new PropertyChangeSupport(this);
    this.id = id;
    this.itemList = itemList;
    this.comment = comment;
    this.dateTime = dateTime;
    this.price = price;
    this.status = status;
  }

  public Order(String comment, DateTime dateTime, double price, String status)
  {
    property = new PropertyChangeSupport(this);
    this.id = -1;
    this.itemList = new ItemList();
    this.comment = comment;
    this.dateTime = dateTime;
    this.price = price;
    this.status = status;

  }

  public int getId()
  {
    return id;
  }

  public String getStatus()
  {
    return status;
  }

  public void payOrder()
  {
    status = "pending";
  }

  public void completeOrder()
  {
    status = "completed";
  }

  public double getPrice()
  {
    return price;
  }

  public void addItem(Item item)
  {
    itemList.add(item);
    price += item.getPrice();
    property.firePropertyChange("add", getPrice(), null);
  }

  public void removeItem(Item item)
  {
    itemList.remove(item);
    price -= item.getPrice();
    property.firePropertyChange("remove", getPrice(), null);
  }

  public void addExtraToItem(Item item, Extra extra)
  {
    itemList.addExtraToItem(item, extra);
  }

  public ItemList getItemList()
  {
    return itemList;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public DateTime getDateTime()
  {
    return dateTime;
  }

  @Override public String toString()
  {
    return "Order{" + "itemList=" + itemList + ", comment='" + comment + '\'' + ", dateTime="
        + dateTime + ", price=" + price + ", status='" + status + '\'' + '}';
  }

  @Override public boolean equals(Object o) //EQUALS METHOD DOES NOT COMPARE IDS
  // INTENTIONALLY. I GUESS.
  {
    if (!(o instanceof Order))
    {
      return false;
    }
    Order other = (Order) o;
    return (this.itemList.equals(other.itemList) && this.status.equals(other.status)
        && Double.compare(this.price, other.price) == 0 && this.dateTime.equals(other.dateTime)
        && this.comment.equals(other.comment));
  }

  public Order copy()
  {
    Order copy = new Order(false);
    copy.id = this.id;
    copy.dateTime = this.dateTime;
    copy.price = this.price;
    copy.comment = this.comment;
    copy.itemList = this.itemList;
    copy.status = this.status;
    return copy;
  }

  public String getTime()
  {
    return dateTime.getTime();
  }

  public void setDateTime(DateTime dateTime)
  {
    this.dateTime = dateTime;
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

  public int getOrderId()
  {
    return id;
  }
}
