package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
  private OrderProperty selectedOrder;

  public BaristaViewModel(Model model)
  {
    this.model = model;
    reset();
    this.selectedOrder = null;
  }

  public void reset()
  {
    this.orders = FXCollections.observableArrayList();
    if(model.getAllPendingOrders()!=null)
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

    reset();
  }

  public void setSelectedOrder(OrderProperty order) {
    this.selectedOrder = order;
  }

  public OrderProperty getSelectedOrder() {
    return selectedOrder;
  }
}
