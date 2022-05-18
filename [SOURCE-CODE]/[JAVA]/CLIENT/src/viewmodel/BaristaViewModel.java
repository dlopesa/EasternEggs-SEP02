package viewmodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import property.OrderProperty;
import utility.Order;

import java.rmi.RemoteException;

public class BaristaViewModel
{
  private Model model;
  private ObservableList<OrderProperty> orders;
  private BaristaHandler handler;

  public BaristaViewModel(Model model, BaristaHandler handler)
  {
    this.model = model;
    this.handler = handler;
    reset();
  }

  public void reset()
  {
    this.orders = FXCollections.observableArrayList();
    for (Order order : model.getAllPendingOrders())
    {
      orders.add(new OrderProperty(order));
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
}
