package viewmodel;

import javafx.beans.property.*;
import model.Model;
import property.ItemProperty;

import java.util.ArrayList;

/**
 * ViewModel class for the AddItemViewController
 *
 * @author Group 1
 * @version 1 May - 2022
 */
public class AddItemViewModel
{
  private Model model;
  private StringProperty name;
  private ArrayList<String> types;
  private StringProperty price;
  private StringProperty description;
  private StringProperty error;
  private StringProperty chosen;

  /**
   * One-argument constructor taking the model as a parameter and initializing it.
   * The StringProperties are created, and the clear() method is called to set
   * the name, price, error and description to an empty String.
   * @param model
   */
  public AddItemViewModel(Model model)
  {
    this.model = model;
    name = new SimpleStringProperty();
    price = new SimpleStringProperty();
    error = new SimpleStringProperty();
    description = new SimpleStringProperty();
    chosen = new SimpleStringProperty();
  }


  /**
   * A void method setting the name, price, error and description StringProperties to an empty String.
   */
  public void clear()

  public void reset()

  {
    try
    {
      types = model.getAllTypes();
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
    name.set("");
    price.set("");
    error.set("");
    description.set("");
  }

  /**
   * A getter method for the item's name
   * @return the item's name as a StringProperty.
   */
  public StringProperty nameProperty()
  {
    return name;
  }

  /**
   * A getter method returning the chosen item.
   * @return the chosen item as a StringProperty.
   */
  public StringProperty chosenProperty()
  {
    return chosen;
  }

  /**
   * A getter method for the item's price.
   * @return the item's price as a StringProperty.
   */
  public StringProperty priceProperty()
  {
    return price;
  }

  /**
   * A getter method for the item's description.
   * @return the item's description as a StringProperty.
   */
  public StringProperty descriptionProperty()
  {
    return description;
  }

  /**
   * A getter method for the error message.
   * @return the error message as a StringProperty.
   */
  public StringProperty errorProperty()
  {
    return error;
  }

  /**
   * A getter method returning the list of types
   * @return an ArrayList of Strings representing the available types.
   */
  public ArrayList<String> getTypes()
  {
    return types;
  }

  /**
   * A void method submitting the addition of an item to the database.
   * @throws IllegalArgumentException if the name, description, chosen item, or price is an empty String.
   */
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
      reset();
    }
    catch (NumberFormatException e)
    {
      error.set("The price has to be a number with a dot.");
    }
    catch (IllegalArgumentException e)
    {
      error.set("The fields cannot be empty.");
    }
    catch (IllegalAccessException e)
    {
      error.set("Access denied.");
    }

  }

}
