package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class LoginViewModel
{
  private StringProperty accessKeyProperty;
  private StringProperty errorProperty;
  private Model model;

  public LoginViewModel(Model model)
  {
    this.model = model;
    accessKeyProperty=new SimpleStringProperty();
  }

  public void reset() {

  }

  public StringProperty getAccessKeyProperty()
  {
    return accessKeyProperty;
  }

  public void setUserType(String type) {
    model.setUserType(type);
  }

  public String getUserType()
  {
    String pwd = getAccessKeyProperty().get();
    return model.getUserType(pwd);
  }



}
