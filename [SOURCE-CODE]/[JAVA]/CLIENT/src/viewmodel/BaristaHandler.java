package viewmodel;

import property.OrderProperty;

/**
 * Intermediary class for the BaristaViewModel and the OrderDetailViewModel classes.
 *
 * @author Group 1
 * @version 1 May - 2022
 */
public class BaristaHandler
{
  private OrderProperty selectedOrder;

  /**
   * Empty constructor.
   */
  public BaristaHandler()
  {
  }

  /**
   * A setter method for setting the selected order to the one in the parameter.
   * @param order the order to be set.
   */
  public void setSelectedOrder(OrderProperty order)
  {
    this.selectedOrder = order;
  }

  /**
   * A getter method for the selected order
   * @return the selected order as an OrderProperty object.
   */
  public OrderProperty getSelectedOrder()
  {
    return selectedOrder;
  }
}
