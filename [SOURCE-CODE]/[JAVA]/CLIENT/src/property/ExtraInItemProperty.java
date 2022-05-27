package property;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * A class that represents the property of the extras in items
 */
public class ExtraInItemProperty
{
  private ItemProperty item;
  private IntegerProperty id;
  private StringProperty name;
  private ArrayList<ExtraProperty> extras;

  /**
   * A four arguments constructor
   * @param item
   * @param extras
   */

  public ExtraInItemProperty(ItemProperty item, ArrayList<ExtraProperty> extras)
  {
    this.item = item;
    this.name = item.nameProperty();
    this.id = item.idProperty();
    this.extras = extras;
  }

  public ItemProperty getItemProperty()
  {
    return item;
  }

  public int getId()
  {
    return id.get();
  }

  public String getExtrasString()
  {
    String s = "";
    for (int i=0; i<extras.size(); i++)
    {
      s += extras.get(i).nameProperty().get();
      if(i!=extras.size()-1)
      {
        s+=", ";
      }
    }
    return s;
  }

  public String getName()
  {
    return name.get();
  }

}
