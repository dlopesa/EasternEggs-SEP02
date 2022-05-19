package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import property.AccessKeyProperty;
import property.ItemProperty;
import property.OrderProperty;
import utility.AccessKey;
import utility.Order;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageAccessKeysViewModel
{
  private ObservableList<AccessKeyProperty> allKeys;
  private Model model;

  public ManageAccessKeysViewModel(Model model)
  {
    this.model = model;
    //allKeys = new ArrayList<>();
    reset();
  }

  public ObservableList<AccessKeyProperty> getAllAccessKeys()
  {
    return allKeys;
  }

  public void removeAccessKey(AccessKeyProperty accessKey)
  {
    model.removeAccessKey(accessKey.getAccessKey());
  }

  //public ObservableList<AccessKeyProperty> getAllAccessKeys()
  //{
  //  ObservableList<AccessKeyProperty> observableList = FXCollections.observableArrayList();
  //  for (AccessKeyProperty allKey : allKeys)
  //  {
  //    observableList.add(allKey);
   // }
   // return observableList;
  //}

  public void reset()
  {

    this.allKeys = FXCollections.observableArrayList();
    try
    {
      for (AccessKey accessKey : model.getAllAccessKey())
      {
        allKeys.add(new AccessKeyProperty(accessKey));
      }
    }
    catch (RemoteException | SQLException e)
    {
      e.printStackTrace();
    }
  }
}
