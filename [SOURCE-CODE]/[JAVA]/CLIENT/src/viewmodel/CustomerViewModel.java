package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import utility.Extra;
import utility.Item;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CustomerViewModel
{
  private Model model;
  private StringProperty error;
  private ArrayList<Item> allItems;

  public CustomerViewModel(Model model)
  {
    this.model = model;
    error = new SimpleStringProperty("");
    try
    {
      allItems = model.getAllExistingItems().getAllItems();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  public void reset()
  {

  }

  public void addToOrder(Item item)
  {
    model.addItemToOrder(item);
  }

  public ArrayList<Item> getItemsByType(String type)
  {
    ArrayList<Item> items = new ArrayList<>();
    for(int i=0; i<allItems.size(); i++)
    {
      if(type.equals(allItems.get(i).getType()))
      {
        items.add(allItems.get(i));
      }
    }
    return items;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

}
