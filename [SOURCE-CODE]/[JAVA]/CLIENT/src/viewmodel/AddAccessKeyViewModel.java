package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import property.AccessKeyProperty;
import utility.AccessKey;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddAccessKeyViewModel
{
  private Model model;
  private StringProperty accessKeyS;
  private ArrayList<String> permission;
  private StringProperty chosen;
  private StringProperty error;

  public AddAccessKeyViewModel(Model model)
  {
    this.model = model;
    accessKeyS = new SimpleStringProperty();
    chosen = new SimpleStringProperty();
    permission = model.getAllPermissions();
    error = new SimpleStringProperty();
    clear();
  }

  public void clear()
  {
    accessKeyS.set("");
    error.set("");
  }

  public StringProperty accessKeyProperty()
  {
    return accessKeyS;
  }

  public StringProperty chosenProperty()
  {
    return chosen;
  }

  public ArrayList<String> getPermission()
  {
    return permission;
  }

  public StringProperty errorProperty()
  {
    return error;
  }

  public void submit()
  {
    try
    {
      if (accessKeyS.get().equals("") || chosen.get() == null)
      {
        throw new IllegalArgumentException();
      }

      for (AccessKey accessKey : model.getAllAccessKey()) {
        if (accessKeyS.get().equals(accessKey.getAccessKey())) {
          throw new IllegalStateException();
        }
      }
      AccessKeyProperty accessKey = new AccessKeyProperty(accessKeyS, chosen);
      model.addAccessKey(accessKey.getAccessKey());
      clear();
    }
    catch (IllegalArgumentException e)
    {
      error.set("The fields cannot be empty.");
    }
    catch (IllegalStateException e)
    {
      error.set("That access key already exists");
    }
    catch (SQLException | RemoteException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
