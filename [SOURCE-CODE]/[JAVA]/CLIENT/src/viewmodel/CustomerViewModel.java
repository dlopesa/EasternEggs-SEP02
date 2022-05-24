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
  private ArrayList<ItemProperty> items;//TODO change to ObservableList, ask Steffen, also VC
  private CustomerHandler handler;
  //private ArrayList<String> types

  public CustomerViewModel(Model model, CustomerHandler handler)
  {
    this.model = model;
    this.handler=handler;
    error = new SimpleStringProperty("");
    items = new ArrayList<>();
    reset();
  }

  public void reset()
  {
    items.clear();
    try
    {
      //todo enhanced
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
    model.addItemToOrder(item.getItem());
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
    model.quitAndCancelOrder();
  }

  public void setItem(ItemProperty item)
  {
    handler.setItem(item);
  }

}