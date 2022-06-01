package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import property.ItemProperty;
import utility.Item;

import java.util.ArrayList;

public class CustomerViewModel
{
  private Model model;
  private StringProperty error;
  private ArrayList<ItemProperty> items;
  private CustomerHandler handler;

  public CustomerViewModel(Model model, CustomerHandler handler)
  {
    this.model = model;
    this.handler=handler;
    error = new SimpleStringProperty("");
    items = new ArrayList<>();
  }

  public void reset()
  {
    items.clear();
    try
    {
      for (Item item: model.getAllExistingItems().getAllItems())
      {
        items.add(new ItemProperty(item));
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  public void addToOrder(ItemProperty item)
  {
    try
    {
      model.addItemToOrder(item.getItem());
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }

  public ObservableList<ItemProperty> getItemsByType(String type)
  {
    ObservableList<ItemProperty> itemsTypeList = FXCollections.observableArrayList();
    for(int i=0; i<items.size(); i++)
    {
      if(type.equals(items.get(i).typeProperty().get()))
      {
        itemsTypeList.add(items.get(i));
      }
    }
    return itemsTypeList;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }


  public void quit()
  {
    try
    {
      model.quitAndCancelOrder();
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }

  public void setItem(ItemProperty item)
  {
    handler.setItem(item);
  }

}