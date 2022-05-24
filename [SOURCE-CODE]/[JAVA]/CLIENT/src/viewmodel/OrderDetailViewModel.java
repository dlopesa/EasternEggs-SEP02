package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import property.ExtraInItemProperty;
import property.ItemProperty;
import property.OrderProperty;

import java.rmi.RemoteException;

public class OrderDetailViewModel
{
  private Model model;
  private BaristaHandler handler;
  private ObservableList<ExtraInItemProperty> extrasInItems;

  public OrderDetailViewModel(Model model, BaristaHandler handler) {
    this.model = model;
    this.handler = handler;
    extrasInItems = FXCollections.observableArrayList();
  }

  public void completeOrder()
  {
    try
    {
      model.completeOrder(handler.getSelectedOrder().getOrder());
    }
    catch (RemoteException | IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }

  public void reset()
  {
    extrasInItems.clear();
    OrderProperty order = handler.getSelectedOrder();
    for(ItemProperty item: order.getItemList())
    {
      if(item.getExtras().size()>0)
      {
        extrasInItems.add(new ExtraInItemProperty(item, item.getExtras()));
      }
    }
  }

  public ObservableList<ExtraInItemProperty> getExtrasInItems()
  {
    return extrasInItems;
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
