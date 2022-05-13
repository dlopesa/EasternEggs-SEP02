package viewmodel;

import model.Model;
import utility.Item;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseViewModel
{
  private ArrayList<Item> allItems;
  private Item chosenItem;
  private Model model;

  public DatabaseViewModel(Model model)
  {
    this.model = model;
    allItems = new ArrayList<>();
    reset();
    chosenItem = null; //TODO figure out how to bind a selected item in a table to a property in the viewmodel
  }

  public void reset() {
    try
    {
      allItems = model.getAllExistingItems().getAllItems();
    }
    catch (RemoteException | SQLException e)
    {
      e.printStackTrace();
    }
  }

  public ArrayList<Item> getAllItems()
  {
    return allItems;
  }
}
