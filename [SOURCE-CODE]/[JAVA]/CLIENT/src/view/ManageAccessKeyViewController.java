package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import property.AccessKeyProperty;
import property.ItemProperty;
import utility.Item;
import viewmodel.ManageAccessKeysViewModel;

public class ManageAccessKeyViewController extends  ViewController
{

  @FXML private TableView accessKeyTable;
  @FXML private TableColumn accessKey;
  @FXML private TableColumn permissionKey;
  private ManageAccessKeysViewModel viewModel;

  @Override protected void init()
  {
  viewModel = getViewModelFactory().getManageAccessKeysViewModel();
  accessKey.setCellValueFactory(new PropertyValueFactory<AccessKeyProperty, StringProperty>("accessKey"));
  permissionKey.setCellValueFactory(new PropertyValueFactory<AccessKeyProperty, StringProperty>("permissionKey"));

  reset();
  }

  public void reset()
  {
    viewModel.reset();
    accessKeyTable.setItems(viewModel.getAllAccessKeys());
  }

  @FXML public void removeItemPressed()
  {
    AccessKeyProperty accessKey = (AccessKeyProperty) accessKeyTable.getSelectionModel().getSelectedItem(); //VIOLAZIONE - use ItemPRoperty
    viewModel.removeAccessKey(accessKey);

    reset();
  }

  @FXML public void backButtonPressed()
  {
    getViewHandler().openView("AdminView.fxml");
  }

  @FXML public void addKeyPressed()
  {
    getViewHandler().openView("AddAccessKey.fxml");
  }
}
