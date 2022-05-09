package viewmodel;

import javafx.beans.property.StringProperty;
import model.Model;

public class LoginViewModel
{
  private StringProperty password;
  private StringProperty userType;
  private Model model;

  public LoginViewModel(Model model)
  {
    this.model = model;
  }

  public void login()
  {

  }

  public StringProperty getPasswordProperty()
  {
    return password;
  }

  public StringProperty getUserTypeProperty()
  {
    return userType;
  }
}
