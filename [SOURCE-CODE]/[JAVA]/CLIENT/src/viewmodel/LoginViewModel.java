package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class LoginViewModel
{
  private StringProperty accessKeyField;
  private Model model;

  public LoginViewModel(Model model)
  {
    this.model = model;
    accessKeyField= new SimpleStringProperty();

  }

  public void login()
  {

    model.setUserType("Admin");

  }

  public StringProperty getAccessKeyProperty()
  {
    return accessKeyField;
  }


}
