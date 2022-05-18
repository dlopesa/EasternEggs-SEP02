package property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utility.Extra;

public class ExtraProperty
{
  private StringProperty name;

  public ExtraProperty(Extra extra)
  {
    name = new SimpleStringProperty(extra.getName());
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
