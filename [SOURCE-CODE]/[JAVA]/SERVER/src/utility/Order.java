package utility;

import java.io.Serializable;

public class Order implements Serializable
{
  private ItemList itemList;
  private String comment;
  private DateTime dateTime;
  private double price;
  private String status;

  public Order(boolean paidWithCash) {
    itemList = new ItemList();
    comment = "";
    dateTime = new DateTime();
    price = 0;
    if (paidWithCash) {
      status = "unpaid";
    }
    else {
      status = "pending";
    }
  }

  public Order(String comment, DateTime dateTime, double price, String status) {
    this.itemList = new ItemList();
    this.comment = comment;
    this.dateTime = dateTime;
    this.price = price;
    this.status = status;
  }

  public String getStatus()
  {
    return status;
  }

  public void payOrder() {
    status = "pending";
  }

  public void completeOrder() {
    status = "completed";
  }

  public double getPrice()
  {
    return price;
  }

  public void addItem(Item item) {
    itemList.add(item);
    price+=item.getPrice();
  }

  public void removeItem(Item item) {
    itemList.remove(item);
    price-=item.getPrice();
  }

  public void addExtraToItem(Item item, Extra extra) {
    itemList.addExtraToItem(item,extra);
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
    return "Order{" + "itemList=" + itemList + ", comment='" + comment + '\''
        + ", dateTime=" + dateTime + ", price=" + price + ", status='" + status
        + '\'' + '}';
  }

  public void setDateTime(DateTime dateTime)
  {
    this.dateTime = dateTime;
  }
}
