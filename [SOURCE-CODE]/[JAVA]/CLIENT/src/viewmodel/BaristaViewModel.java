package viewmodel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import property.OrderProperty;
import utility.Order;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public class BaristaViewModel implements PropertyChangeListener
{
  private Model model;
  private ObservableList<OrderProperty> orders;
  private BaristaHandler handler;
  private boolean open;

  public BaristaViewModel(Model model, BaristaHandler handler)
  {
    this.model = model;
    model.addListener(this);
    this.handler = handler;
    this.orders = FXCollections.observableArrayList();
    this.open = false;
  }

  public void open() {
    this.open = true;
  }

  public void close() {
    this.open = false;
  }

  public void reset()
  {
    orders.clear();
    try
    {
      if (model.getAllPendingOrders() != null)
      {
        for (Order order : model.getAllPendingOrders())
        {
          orders.add(new OrderProperty(order));
        }
      }
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }
  public ObservableList<OrderProperty> getOrders()
  {
    return orders;
  }

  public void setSelectedOrder(OrderProperty order)
  {
    handler.setSelectedOrder(order);
  }


  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(()->
    {
      if(evt.getPropertyName().equals("change") && open)
      {
        reset();
      }
    });
  }
}
