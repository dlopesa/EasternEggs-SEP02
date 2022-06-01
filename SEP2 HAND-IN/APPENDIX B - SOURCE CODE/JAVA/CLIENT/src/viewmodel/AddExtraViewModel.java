package viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import utility.Extra;

public class AddExtraViewModel
{
  private StringProperty name;
  private BooleanProperty coffee;
  private BooleanProperty tea;
  private BooleanProperty snack;
  private BooleanProperty smoothie;
  private Model model;

  public AddExtraViewModel(Model model)
  {
    this.model = model;
    this.name = new SimpleStringProperty();
    this.coffee = new SimpleBooleanProperty();
    this.tea = new SimpleBooleanProperty();
    this.snack = new SimpleBooleanProperty();
    this.smoothie = new SimpleBooleanProperty();
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public BooleanProperty coffeeProperty()
  {
    return coffee;
  }

  public BooleanProperty teaProperty()
  {
    return tea;
  }

  public BooleanProperty snackProperty()
  {
    return snack;
  }

  public BooleanProperty smoothieProperty()
  {
    return smoothie;
  }

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
