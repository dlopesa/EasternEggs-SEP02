package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import property.OrderProperty;
import utility.Order;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CashierViewModel implements PropertyChangeListener
{
  private Model model;
  private ObservableList<OrderProperty> unpaidOrders;
  private ObservableList<OrderProperty> pendingOrders;
  private CashierHandler handler;

  public CashierViewModel(Model model, CashierHandler handler)
  {
    this.model = model;
    model.addListener(this);
    this.handler = handler;
    this.unpaidOrders = FXCollections.observableArrayList();
    this.pendingOrders = FXCollections.observableArrayList();
    reset();
  }

  public void reset()
  {
    unpaidOrders.clear();
    for (Order order : model.getAllUnpaidOrders())
    {
      unpaidOrders.add(new OrderProperty(order));
    }
    pendingOrders.clear();
    for (Order order : model.getAllPendingOrders())
    {
      pendingOrders.add(new OrderProperty(order));
    }
  }

  public ObservableList<OrderProperty> getUnpaidOrders()
  {
    return unpaidOrders;
  }

  public ObservableList<OrderProperty> getPendingOrders()
  {
    return pendingOrders;
  }

  public void setSelectedUnpaidOrder(OrderProperty order)
  {
    handler.setSelectedUnpaidOrder(order);
  }

  public void setSelectedPendingOrder(OrderProperty order)
  {
    handler.setSelectedPendingOrder(order);
  }

  public void cancelOrder()
  {
    model.cancelUnpaidOrder(handler.getSelectedUnpaidOrder().getOrder());
  }

  public void markOrderAsPaid()
  {
    model.acceptPayment(handler.getSelectedUnpaidOrder().getOrder());
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
