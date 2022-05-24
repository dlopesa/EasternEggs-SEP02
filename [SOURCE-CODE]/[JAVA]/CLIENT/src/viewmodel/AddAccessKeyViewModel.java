package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import property.AccessKeyProperty;
import utility.AccessKey;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ViewModel class for the AddAccessKeyViewController.
 *
 * @author Group 1
 * @version 1 - May 2022
 */
public class AddAccessKeyViewModel
{
  private Model model;
  private StringProperty accessKeyS;
  private ArrayList<String> permission;
  private StringProperty chosen;
  private StringProperty error;

  /**
   * One-argument constructor taking the model initializing the StringProperties and calling the method clear(),
   * which sets the accessKey and error StringProperties to an empty String.
   *
   * @param model the model
   *
   */
  public AddAccessKeyViewModel(Model model)
  {
    this.model = model;
    accessKeyS = new SimpleStringProperty();
    chosen = new SimpleStringProperty();
    permission = model.getAllPermissions();
    error = new SimpleStringProperty();
    clear();
  }

  /**
   * A void method for setting the accessKey and error StringProperties to an empty String.
   */
  public void clear()
  {
    accessKeyS.set("");
    error.set("");
  }

  /**
   * A getter method for the accessKeyProperty.
   * @return the accessKey as a StringProperty.
   */
  public StringProperty accessKeyProperty()
  {
    return accessKeyS;
  }

  /**
   * A getter method for the chosen access category.
   * @return the chosen permission category as a StringProperty.
   */
  public StringProperty chosenProperty()
  {
    return chosen;
  }

  /**
   * A getter method for the permission.
   * @return the user's permission as a StringProperty.
   */
  public ArrayList<String> getPermission()
  {
    return permission;
  }

  /**
   * A getter method for the error message.
   * @return the error message as a StringProperty
   */
  public StringProperty errorProperty()
  {
    return error;
  }

  /**
   * A void method for submitting a new accessKey.
   * @throws IllegalArgumentException if the accessKey is an empty String, or if
   * no permission category has been chosen.
   * @throws IllegalStateException if the accessKey is not unique.
   * A new access key is created and passed on to the model to be stored, then the view is cleared
   * by the method clear().
   */
  public void submit()
  {
    try
    {
      if (accessKeyS.get().equals("") || chosen.get() == null)
      {
        throw new IllegalArgumentException();
      }

      for (AccessKey accessKey : model.getAllAccessKey())
      {
        if (accessKeyS.get().equals(accessKey.getAccessKey()))
        {
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
