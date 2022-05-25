package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import property.ExtraProperty;
import property.ItemProperty;
import utility.Item;

import java.util.ArrayList;

public class ExtraViewModel
{

  private Model model;
  private CustomerHandler handler;
  private ObservableList<ExtraProperty> availableExtras;
  private ObservableList<ExtraProperty> addedExtras;
  private ItemProperty currentItem;
  private StringProperty name;

  public ExtraViewModel(Model model, CustomerHandler handler)
  {
    this.model = model;
    this.handler = handler;
    name=new SimpleStringProperty();
    availableExtras = FXCollections.observableArrayList();
    addedExtras = FXCollections.observableArrayList();
  }

  public void reset()
  {
    currentItem = handler.getItem();
    name=currentItem.nameProperty();
    try
    {
      setList(availableExtras, currentItem.typeProperty().get());
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }


  public StringProperty getNameProperty()
  {
    return name;
  }

  public void setList(ObservableList<ExtraProperty> extraList, String type)
      throws IllegalAccessException
  {
    extraList.clear();
    for (int i = 0; i < model.getAllExtrasByType(type).size(); i++)
    {
      extraList.add(new ExtraProperty(model.getAllExtrasByType(type).get(i)));
    }
  }

  public ObservableList<ExtraProperty> getAvailableExtras()
  {
    return availableExtras;
  }

  public ObservableList<ExtraProperty> getAddedExtras()
  {
    return addedExtras;
  }

  public void addExtraToItem(ExtraProperty extra)
  {
    addedExtras.add(extra);
  }

  public void removeExtraFromItem(ExtraProperty extra)
  {
    addedExtras.remove(extra);
  }

  public void addItemToOrder()
  {
    if (addedExtras.size() > 0)
    {
      for (int i = 0; i < addedExtras.size(); i++)
      {
        try
        {
          model.addExtraToItem(addedExtras.get(i).getExtra(),
              currentItem.getItem());
        }
        catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
      }
    }
    try
    {
      model.addItemToOrder(currentItem.getItem());
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
    addedExtras.clear();
    availableExtras.clear();
  }
}
