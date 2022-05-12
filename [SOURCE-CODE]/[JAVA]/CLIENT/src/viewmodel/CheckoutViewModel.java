package viewmodel;

import property.ItemProperty;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CheckoutViewModel
    implements PropertyChangeListener, UnnamedPropertyChangeSubject
{
  private Model model;
  private StringProperty totalPrice;
  private StringProperty error;
  private PropertyChangeSupport property;
  private ObservableList<ItemProperty> items;

  public CheckoutViewModel(Model model)
  {
    this.model = model;
    model.addListener(this);
    property = new PropertyChangeSupport(this);
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
    System.out.println("VM: getting this: " + item);
    model.removeItemFromOrder(item.getItem());
    //This should fire an event inside model or here
    //To be caught by viewController and reset the table.
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      populatingItemsFromOrder();
      double tempPrice = (double) evt.getOldValue();
      System.out.println(tempPrice);
      totalPrice.set(String.valueOf(tempPrice));
      property.firePropertyChange(evt.getPropertyName(), evt.getOldValue(),
          evt.getNewValue());
    });
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
