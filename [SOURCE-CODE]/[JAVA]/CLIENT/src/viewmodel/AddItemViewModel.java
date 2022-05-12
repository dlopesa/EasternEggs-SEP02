package viewmodel;

import com.sun.javafx.collections.ImmutableObservableList;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import model.Model;
import property.ItemProperty;
import utility.Item;

import java.util.ArrayList;

public class AddItemViewModel
{
  private Model model;
  private StringProperty name;
  private ArrayList<String> types;
  private StringProperty price;
  private StringProperty description;
  private StringProperty error;
  private StringProperty chosen;

  public AddItemViewModel(Model model)
  {
    this.model = model;
    name = new SimpleStringProperty();
    price = new SimpleStringProperty();
    error = new SimpleStringProperty();
    description = new SimpleStringProperty();
    chosen = new SimpleStringProperty();
    types = model.getAllTypes();
    clear();
  }

  public void clear()
  {
    name.set("");
    price.set("");
    error.set("");
    description.set("");
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

  public ArrayList<String> getTypes()
  {
    return types;
  }

  public void submit()
  {
    try
    {
      if (name.get().equals("") || description.get().equals("") || chosen.get()
          .equals("") || price.get().equals(""))
      {
        throw new IllegalArgumentException();
      }
      double itemPrice = Double.parseDouble(price.get());
      DoubleProperty priceProperty = new SimpleDoubleProperty(itemPrice);
      ItemProperty item = new ItemProperty(new SimpleIntegerProperty(-1), name,
          chosen, priceProperty, description);
      model.addItemToProductList(item.getItem());
      clear();
    }
    catch (NumberFormatException e)
    {
      error.set("The price has to be a number with a dot.");
    }
    catch (IllegalArgumentException e)
    {
      error.set("The fields cannot be empty.");
    }

  }

}
