package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.rmi.RemoteException;
import java.sql.SQLException;

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

  public String getUserType()
  {
    String pwd = accessKeyProperty.get();
    String ak = model.getUserType(pwd);
    return ak;
  }



}
