package viewmodel;

import model.Model;
import property.OrderProperty;

public class CashierHandler
{
  private OrderProperty selectedUnpaidOrder;
  private OrderProperty selectedPendingOrder;

  public void setSelectedUnpaidOrder(OrderProperty order)
  {
    this.selectedUnpaidOrder = order;
  }

  public OrderProperty getSelectedUnpaidOrder()
  {
    return selectedUnpaidOrder;
  }

  public void setSelectedPendingOrder(OrderProperty order)
  {
    this.selectedPendingOrder = order;
  }

  public OrderProperty getSelectedPendingOrder()
  {
    return selectedPendingOrder;
  }

}
