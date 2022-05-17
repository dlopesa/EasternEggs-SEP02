package viewmodel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import property.OrderProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DisplayViewModel implements PropertyChangeListener

{
  private Model model;
  private ObservableList<OrderProperty> pendingList;
  private ObservableList<OrderProperty> completedList;

  public DisplayViewModel(Model model)
  {
    this.model=model;
    model.addListener(this);
    pendingList= FXCollections.observableArrayList();
    completedList= FXCollections.observableArrayList();
    reset();
  }

  public void reset()
  {
    pendingList.clear();
    for(int i=0; i<model.getAllPendingOrders().size(); i++)
    {
      pendingList.add(new OrderProperty(model.getAllPendingOrders().get(i)));
    }
    completedList.clear();
    for(int i=0; i<model.getAllCompletedOrders().size(); i++)
    {
      completedList.add(new OrderProperty(model.getAllCompletedOrders().get(i)));
    }
  }

  public ObservableList<OrderProperty> getPendingList()
  {
    return pendingList;
  }

  public ObservableList<OrderProperty> getCompletedList()
  {
    return completedList;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(()->{
      if(evt.getPropertyName().equals("change"))
      {
        reset();
      }
    });
  }
}
