package viewmodel;

import property.OrderProperty;

public class BaristaHandler
{
  private OrderProperty selectedOrder;

  public BaristaHandler()
  {
  }

  public void setSelectedOrder(OrderProperty order)
  {
    this.selectedOrder = order;
  }

  public OrderProperty getSelectedOrder()
  {
    return selectedOrder;
  }
}
