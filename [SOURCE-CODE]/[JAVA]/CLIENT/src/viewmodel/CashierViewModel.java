package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import property.OrderProperty;
import utility.Order;

public class CashierViewModel
{
  private Model model;
  private ObservableList<OrderProperty> unpaidOrders;
  private ObservableList<OrderProperty> pendingOrders;
  private CashierHandler handler;

  public CashierViewModel(Model model, CashierHandler handler)
  {
    this.model = model;
    reset();
    this.handler = handler;
  }

  public void reset()
  {
    this.unpaidOrders = FXCollections.observableArrayList();
    for (Order order : model.getAllUnpaidOrders())
    {
      unpaidOrders.add(new OrderProperty(order));
    }
    this.pendingOrders = FXCollections.observableArrayList();
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
}
