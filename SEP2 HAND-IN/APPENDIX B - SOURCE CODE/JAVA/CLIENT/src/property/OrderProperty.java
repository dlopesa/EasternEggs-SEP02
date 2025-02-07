package property;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.DateTime;
import utility.Item;
import utility.ItemList;
import utility.Order;

import java.util.ArrayList;
import java.util.Objects;

public class OrderProperty
{
  private IntegerProperty id;
  private StringProperty comment;
  private ObjectProperty<DateTime> dateTime;
  private DoubleProperty price;
  private StringProperty status;
  private ObservableList<ItemProperty> itemList;
  private StringProperty time;

  public StringProperty timeProperty()
  {
    return time;
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    OrderProperty that = (OrderProperty) o;
    return Objects.equals(id, that.id) && Objects.equals(comment, that.comment)
        && Objects.equals(dateTime, that.dateTime) && Objects.equals(price,
        that.price) && Objects.equals(status, that.status) && Objects.equals(
        itemList, that.itemList) && Objects.equals(time, that.time);
  }

  @Override public int hashCode()
  {
    return Objects.hash(id, comment, dateTime, price, status, itemList, time);
  }

  public OrderProperty(Order order)
  {
    this.id = new SimpleIntegerProperty(order.getId());
    this.comment = new SimpleStringProperty(order.getComment());
    this.dateTime = new SimpleObjectProperty<DateTime>(order.getDateTime());
    this.price = new SimpleDoubleProperty(order.getPrice());
    this.status = new SimpleStringProperty(order.getStatus());
    this.time = new SimpleStringProperty(order.getTime());

    ArrayList<ItemProperty> newItemList = new ArrayList<>();

    for (Item item : order.getItemList().getAllItems())
    {
      newItemList.add(new ItemProperty(item));
    }

    this.itemList = FXCollections.observableList(newItemList);

  }

  public IntegerProperty idProperty()
  {
    return id;
  }

  public StringProperty commentProperty()
  {
    return comment;
  }

  public ObjectProperty<DateTime> dateTimeProperty()
  {
    return dateTime;
  }

  public DoubleProperty priceProperty()
  {
    return price;
  }



  public StringProperty statusProperty()
  {
    return status;
  }

  public ObservableList<ItemProperty> getItemList()
  {
    return itemList;
  }

  public Order getOrder()
  {

    ItemList newItemList = new ItemList();
    for (ItemProperty item : itemList)
    {
      newItemList.add(item.getItem());
    }

    return new Order(id.get(), newItemList, comment.get(),
        dateTime.get(), price.get(),
        status.get());
  }

  @Override public String toString()
  {
    return "OrderProperty{" + "id=" + id + ", comment=" + comment
        + ", dateTime=" + dateTime + ", price=" + price + ", status=" + status
        + ", itemList=" + itemList + ", time=" + time + '}';
  }
}
