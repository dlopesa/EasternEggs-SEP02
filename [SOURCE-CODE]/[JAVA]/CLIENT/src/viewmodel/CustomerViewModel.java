package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import utility.Extra;
import utility.Item;

public class CustomerViewModel
{
  private Model model;
  private StringProperty name;
  private StringProperty price;
  private StringProperty error;
  private StringProperty extra;

  public CustomerViewModel(Model model)
  {
    this.model = model;
    name = new SimpleStringProperty();
    price = new SimpleStringProperty();
    error = new SimpleStringProperty();
    extra = new SimpleStringProperty();
    name.set("Latte Machination");
    price.set("10.00");
    error.set("");
    extra.set("caramel, chocolate");
  }

  public void addItemToOrder()
  {
    Platform.runLater(() -> {
      double tempPrice = Double.parseDouble(price.get());
      Item holder = new Item(6, name.get(), "coffee", tempPrice);
      model.addItemToOrder(holder);
    });
  }

  public void submitOrder()
  {

    Platform.runLater(() -> {

      {
        try
        {
          model.submitOrder();
        }

        catch (NullPointerException e)
        {
          error.set("Cannot submit empty order. Please add items.");
        }
      }
    });
  }



  public StringProperty getNameProperty()
  {
    return name;
  }

  public StringProperty getPriceProperty()
  {
    return price;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

  public StringProperty getExtraProperty()
  {
    return extra;
  }
}
