package viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import utility.Extra;

/**
 * ViewModel class for the AddExtraViewController
 *
 * @author Group 1
 * @version 1 - May 2022
 */
public class AddExtraViewModel
{
  private StringProperty name;
  private BooleanProperty coffee;
  private BooleanProperty tea;
  private BooleanProperty snack;
  private BooleanProperty smoothie;
  private Model model;

  /**
   * One-argument constructor taking the model and initializing the StringProperty
   * and the BooleanProperties.
   *
   * @param model the model
   *
   */
  public AddExtraViewModel(Model model)
  {
    this.model = model;
    this.name = new SimpleStringProperty();
    this.coffee = new SimpleBooleanProperty();
    this.tea = new SimpleBooleanProperty();
    this.snack = new SimpleBooleanProperty();
    this.smoothie = new SimpleBooleanProperty();
  }

  /**
   * A getter method returning the name of the extra.
   * @return the name as a StringProperty.
   */
  public StringProperty nameProperty()
  {
    return name;
  }

  /**
   * A getter method.
   * @return a BooleanProperty showing if the type of the item is of "coffee".
   */
  public BooleanProperty coffeeProperty()
  {
    return coffee;
  }

  /**
   * A getter method.
   * @return a BooleanProperty showing if the type of the item is of "tea".
   */
  public BooleanProperty teaProperty()
  {
    return tea;
  }

  /**
   * A getter method
   * @return a BooleanProperty showing if the type of the item is of "snack".
   */
  public BooleanProperty snackProperty()
  {
    return snack;
  }

  /**
   * A getter method
   * @return a BooleanProperty showing if the type of the item is of "smoothie".
   */
  public BooleanProperty smoothieProperty()
  {
    return smoothie;
  }

  /**
   * A void method adding an extra to the list of available extras based on the item's type.
   */
  public void addExtra()
  {
    String name = nameProperty().get();
    Extra extra = new Extra(name);
    if (coffeeProperty().get())
    {
      extra.addAvailableType("coffee");
    }
    if (teaProperty().get())
    {
      extra.addAvailableType("tea");
    }
    if (snackProperty().get())
    {
      extra.addAvailableType("snack");
    }
    if (smoothieProperty().get())
    {
      extra.addAvailableType("smoothie");
    }
    try
    {
      model.addExtraToExtraList(extra);
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }

}
