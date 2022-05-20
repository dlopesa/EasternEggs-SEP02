package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import property.ItemProperty;

import java.util.ArrayList;

public class DescriptionViewModel
{
  private Model model;
  private StringProperty name;
  private StringProperty price;
  private StringProperty description;
  private StringProperty error;
  private StringProperty chosen;
  private CustomerHandler handler;

  public DescriptionViewModel(Model model, CustomerHandler handler)
  {
    this.model = model;
    this.handler=handler;
    name = new SimpleStringProperty();
    price = new SimpleStringProperty();
    error = new SimpleStringProperty();
    description = new SimpleStringProperty();
    chosen = new SimpleStringProperty();
  }

  public void reset()
  {
    ItemProperty item = handler.getItem();
    name.set(item.nameProperty().get());
    price.set(String.valueOf(item.priceProperty().get()));
    error.set("");
    description.set(item.descriptionProperty().get());
    chosen.set(item.typeProperty().get());
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public StringProperty chosenProperty()
  {
    return chosen;
  }

  public StringProperty priceProperty()
  {
    return price;
  }

  public StringProperty descriptionProperty()
  {
    return description;
  }

  public StringProperty errorProperty()
  {
    return error;
  }

}
