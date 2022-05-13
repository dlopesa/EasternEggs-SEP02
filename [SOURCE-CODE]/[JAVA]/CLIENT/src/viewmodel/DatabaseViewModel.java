package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import property.ItemProperty;
import utility.Item;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class DatabaseViewModel
{
  private ArrayList<ItemProperty> allItems;
  private Model model;

  public DatabaseViewModel(Model model)
  {
    this.model = model;
    allItems = new ArrayList<>();
    reset();
  }

  public void reset()
  {
    allItems.clear();
    try
    {
      for (int i = 0; i < model.getAllExistingItems().getAllItems().size(); i++)
      {
        allItems.add(new ItemProperty(model.getAllExistingItems().getAllItems().get(i)));
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  public ObservableList<ItemProperty> getAllItems()
  {
    ObservableList<ItemProperty> observableList = FXCollections.observableArrayList();
    for (int i = 0; i < allItems.size(); i++)
    {
      observableList.add(allItems.get(i));
    }
    return observableList;
  }

  public void removeItem(ItemProperty item)
  {
    model.removeItemFromProductList(item.getItem());
  }
}
