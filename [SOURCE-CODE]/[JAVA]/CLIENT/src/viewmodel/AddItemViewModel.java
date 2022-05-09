package viewmodel;

import com.sun.javafx.collections.ImmutableObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import model.Model;
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
    types = new ArrayList<>();
    clear();
  }

  public void clear()
  {
    name.set("");
    price.set("");
    error.set("");
    description.set("");
    types.add("coffee");
    types.add("tea");
    types.add("snack");
    types.add("smoothie");
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
    Item item = new Item(name.get(), chosen.get(),
        Double.parseDouble(price.get()), description.get());
    model.addItemToProductList(item);
  }

}
