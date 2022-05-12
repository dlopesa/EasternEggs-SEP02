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

  public DescriptionViewModel(Model model)
  {
    this.model = model;
    name = new SimpleStringProperty();
    price = new SimpleStringProperty();
    error = new SimpleStringProperty();
    description = new SimpleStringProperty();
    chosen = new SimpleStringProperty();
  }

  public void reset()
  {
    name.set("");
    price.set("");
    error.set("");
    description.set("");
    chosen.set("");
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

  public void setItemProperty(ItemProperty item)
  {
    name = item.nameProperty();
    chosen = item.typeProperty();
    price = new SimpleStringProperty(String.valueOf(item.priceProperty().get()));
    description = item.descriptionProperty();
  }
}
