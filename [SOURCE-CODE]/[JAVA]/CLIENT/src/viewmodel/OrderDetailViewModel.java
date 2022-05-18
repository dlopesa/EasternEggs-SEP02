package viewmodel;

import model.Model;
import property.OrderProperty;
import utility.Order;

import java.rmi.RemoteException;

public class OrderDetailViewModel
{
  private Model model;
  private BaristaHandler handler;

  public OrderDetailViewModel(Model model, BaristaHandler handler) {
    this.model = model;
    this.handler = handler;
  }

  public void completeOrder(Order order)
  {
    try
    {
      model.completeOrder(order);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  public void setSelectedOrder(OrderProperty order) {
    handler.setSelectedOrder(order);
  }

  public OrderProperty getSelectedOrder() {
    return handler.getSelectedOrder();
  }

  public BaristaHandler getHandler() {
    return handler;
  }
}
