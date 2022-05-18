package viewmodel;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import property.ExtraProperty;
import property.ItemProperty;

import java.util.ArrayList;

public class ExtraViewModel
{

  private Model model;

  private ItemProperty currentItem;
  private ArrayList<ExtraProperty> extras;

  public ExtraViewModel(Model model)
  {
    this.model = model;
    extras = new ArrayList<>();
  }

  public void reset()
  {
    // TODO: 18/05/2022  
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

  public ObservableList<ExtraProperty> getExtrasByType(String type)
  {

    ObservableList<ExtraProperty> extrasTypeList = FXCollections.observableArrayList();
    for (int i = 0; i < extras.size(); i++)
    {
      if (type.equals(extras.get(i).typeProperty().get()))
      {
        extrasTypeList.add(extras.get(i));
      }
    }
    return extrasTypeList;
  }
}
