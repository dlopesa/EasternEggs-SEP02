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
  //TODO: baristaVM still has dependency with Utility package
  private Model model;
  private ObservableList<OrderProperty> orders;
  private BaristaHandler handler;

  public BaristaViewModel(Model model, BaristaHandler handler)
  {
    this.model = model;
    model.addListener(this);
    this.handler = handler;
    this.orders = FXCollections.observableArrayList();
    reset();
  }

  public void reset()
  {
    orders.clear();
    if (model.getAllPendingOrders() != null)
    {
      for (Order order : model.getAllPendingOrders())
      {
        orders.add(new OrderProperty(order));
      }
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

  public OrderProperty getSelectedOrder()
  {
    return handler.getSelectedOrder();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(()->
    {
      if(evt.getPropertyName().equals("change"))
      {
        reset();
      }
    });
  }
}
