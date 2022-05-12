package viewmodel;

import model.Model;
import utility.Item;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class DatabaseViewModel
{
  private ArrayList<Item> allItems;
  private Model model;

  public DatabaseViewModel(Model model)
  {
    this.model = model;
    allItems = new ArrayList<>();
    reset();

  }

  public void reset()
  {
    try
    {
      allItems = model.getAllExistingItems().getAllItems();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  public ArrayList<Item> getAllItems()
  {
    return allItems;
  }

  public void removeItem(Item item)
  {
    model.removeItemFromProductList(item);
  }
}
