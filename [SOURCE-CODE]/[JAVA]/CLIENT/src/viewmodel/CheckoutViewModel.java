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
    double tempPrice = model.getOrder().getPrice();
    totalPrice.set(String.valueOf(tempPrice));
    error = new SimpleStringProperty("");
    items = FXCollections.observableArrayList();
    populatingItemsFromOrder();
  }

  private void populatingItemsFromOrder()
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
    model.removeItemFromOrder(item.getItem());
  }

  public void quit()
  {
    model.quitAndCancelOrder();
  }

  public int submit()
  {
    return model.submitOrder();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if (!evt.getPropertyName().equals("change"))
      {
        populatingItemsFromOrder();
        double tempPrice = (double) evt.getOldValue();
        totalPrice.set(String.valueOf(tempPrice));
      }
    });
  }

}
