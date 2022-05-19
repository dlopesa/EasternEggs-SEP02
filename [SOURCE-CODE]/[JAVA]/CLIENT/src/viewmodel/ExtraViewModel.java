package viewmodel;

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

  private ItemProperty currentItem;
  private ObservableList<ExtraProperty> availableExtras;
  private ObservableList<ExtraProperty> addedExtras;

  public ExtraViewModel(Model model)
  {
    this.model = model;
    availableExtras = FXCollections.observableArrayList();
    addedExtras = FXCollections.observableArrayList();
  }

  public void reset()
  {
    setList(availableExtras, currentItem.typeProperty().get());
  }

  public void setItem(ItemProperty item)
  {
    this.currentItem = item;
  }

  public StringProperty getNameProperty()
  {
    return currentItem.nameProperty();
  }

  public StringProperty getTypeProperty()
  {
    return currentItem.typeProperty();
  }

  public void setList(ObservableList<ExtraProperty> extraList, String type)
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
        model.addExtraToItem(addedExtras.get(i).getExtra(),
            currentItem.getItem());
      }
    }
    System.out.println(currentItem.getItem());
    model.addItemToOrder(currentItem.getItem());
  }
}
