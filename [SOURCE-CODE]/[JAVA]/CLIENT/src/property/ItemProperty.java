package property;

import javafx.beans.property.*;
import utility.Item;

public class ItemProperty
{
  private IntegerProperty id;
  private StringProperty name;
  private StringProperty type;
  private DoubleProperty price;
  private StringProperty description;

  public ItemProperty(Item item)
  {
    id = new SimpleIntegerProperty(item.getId());
    name = new SimpleStringProperty(item.getName());
    type = new SimpleStringProperty(item.getType());
    price = new SimpleDoubleProperty(item.getPrice());
    description = new SimpleStringProperty(item.getDescription());
  }

  public ItemProperty(IntegerProperty id, StringProperty name,
      StringProperty type, DoubleProperty price, StringProperty description)
  {
    this.id = id;
    this.name = name;
    this.type = type;
    this.price = price;
    this.description = description;
  }

  public IntegerProperty idProperty()
  {
    return id;
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public StringProperty typeProperty()
  {
    return type;
  }

  public DoubleProperty priceProperty()
  {
    return price;
  }

  public StringProperty descriptionProperty()
  {
    return description;
  }

  public Item getItem()
  {
    return new Item(id.get(), name.get(), type.get(), price.get(),
        description.get());
  }

}
