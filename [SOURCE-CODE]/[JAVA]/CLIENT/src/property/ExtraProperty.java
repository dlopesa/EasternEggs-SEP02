package property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.Extra;

import java.io.Serializable;

/**
 * A class that represents an extra property.
 */
public class ExtraProperty implements Serializable
{
  private StringProperty name;
  private ObservableList<StringProperty> typesAvailable;

  /**
   * A three arguments constructor.
   * the for statement gets all the available types and stores it into an observable list
   * @param extra
   */
  public ExtraProperty(Extra extra)
  {
    name = new SimpleStringProperty(extra.getName());
    typesAvailable = FXCollections.observableArrayList();
    for (String type : extra.getAvailableTypes()) {
      typesAvailable.add(new SimpleStringProperty(type));
    }
  }

  public ObservableList<StringProperty> getTypesAvailable()
  {
    return typesAvailable;
  }

  public String getTypes() {
    String s = "";
    for (int i = 0; i < getTypesAvailable().size(); i++) {
      s+= getTypesAvailable().get(i).get();
      if (i < getTypesAvailable().size()-1) {
        s+= ", ";
      }
    }
    return s;
  }

  public ExtraProperty(String name)
  {
    this.name= new SimpleStringProperty(name);
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public Extra getExtra()
  {
    return new Extra(name.get());
  }
}
