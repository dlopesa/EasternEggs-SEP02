package property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utility.Extra;

public class ExtraProperty
{
  private StringProperty name;
  private StringProperty type;

  public ExtraProperty(Extra extra)
  {
    name = new SimpleStringProperty(extra.getName());
    type= new SimpleStringProperty(extra.getType());
  }

  public StringProperty nameProperty(){
    return name;
  }

  public StringProperty typeProperty(){
    return type;
  }

  public Extra getExtra(){
    return new Extra(name.get(),type.get());
  }
}
