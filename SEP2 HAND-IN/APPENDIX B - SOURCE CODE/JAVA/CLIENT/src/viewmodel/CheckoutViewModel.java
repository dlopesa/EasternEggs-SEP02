package viewmodel;

import property.ItemProperty;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CheckoutViewModel implements PropertyChangeListener
{
  private Model model;
  private StringProperty totalPrice;
  private StringProperty error;
  private ObservableList<ItemProperty> items;

  public CheckoutViewModel(Model model)
  {
    this.model = model;
    model.addListener(this);
    totalPrice = new SimpleStringProperty();
    error = new SimpleStringProperty("");
    items = FXCollections.observableArrayList();
  }

  public void reset() {

    double tempPrice = 0;
    try
    {
      tempPrice = model.getOrder().getPrice();
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
    totalPrice.set(String.valueOf(tempPrice));

    try
    {
      populatingItemsFromOrder();
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }

  private void populatingItemsFromOrder() throws IllegalAccessException
  {
    items.clear();
    for (int i = 0;
         i < model.getOrder().getItemList().getAllItems().size(); i++)
    {
      ItemProperty itemProperty = new ItemProperty(
          model.getOrder().getItemList().getAllItems().get(i));
      items.add(itemProperty);
    }
  }

  public ObservableList<ItemProperty> getItemsInOrder()
  {
    return items;
  }

  public StringProperty getTotalPrice()
  {
    return totalPrice;
  }

  public StringProperty getError()
  {
    return error;
  }

  public void removeFromOrder(ItemProperty item)
  {
    try
    {
      model.removeItemFromOrder(item.getItem());
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }

  public void quit()
  {
    try
    {
      model.quitAndCancelOrder();
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }

  public int submit()
  {
    try
    {
      return model.submitOrder();
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
    return -100;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if (!evt.getPropertyName().equals("change"))
      {
        try
        {
          populatingItemsFromOrder();
        }
        catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
        double tempPrice = (double) evt.getOldValue();
        totalPrice.set(String.valueOf(tempPrice));
      }
    });
  }

}
