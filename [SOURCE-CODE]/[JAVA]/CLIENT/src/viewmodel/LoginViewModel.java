package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class LoginViewModel
{
  private StringProperty accessKeyProperty;
  private Model model;

  public LoginViewModel(Model model)
  {
    this.model = model;
    accessKeyProperty=new SimpleStringProperty();
  }



  public StringProperty getAccessKeyProperty()
  {
    return accessKeyProperty;
  }


}
