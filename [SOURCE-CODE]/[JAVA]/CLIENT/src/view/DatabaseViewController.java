package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import property.ItemProperty;
import utility.Item;
import viewmodel.DatabaseViewModel;

public class DatabaseViewController extends ViewController
{
  @FXML private TableView itemTable;
  @FXML private TableColumn idCol;
  @FXML private TableColumn nameCol;
  @FXML private TableColumn typeCol;
  @FXML private TableColumn priceCol;
  private DatabaseViewModel viewModel;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getDatabaseViewModel();
    idCol.setCellValueFactory(new PropertyValueFactory<ItemProperty, IntegerProperty>("id"));
    nameCol.setCellValueFactory(new PropertyValueFactory<ItemProperty, StringProperty>("name"));
    typeCol.setCellValueFactory(new PropertyValueFactory<ItemProperty, StringProperty>("type"));
    priceCol.setCellValueFactory(
        new PropertyValueFactory<ItemProperty, DoubleProperty>("price"));

    reset();

  }

  public void reset()
  {
    viewModel.reset();
    itemTable.setItems(viewModel.getAllItems());
  }

  @FXML public void removeItemPressed()
  {
    ItemProperty item = (ItemProperty) itemTable.getSelectionModel().getSelectedItem(); //VIOLAZIONE - use ItemPRoperty
    //TODO add an instance variable to the View Model of the selected item and make the removeItem method not take any arguments and just use the selected Item stored in VM.
    viewModel.removeItem(item);

    reset();
  }

  @FXML public void editItemPressed()
  {
    //Todo future use case
  }

  @FXML public void backPressed()
  {
    getViewHandler().openView("LoginView.fxml");
  }

  @FXML public void addItemPressed()
  {
    getViewHandler().openView("AddItemView.fxml");
  }

  @FXML public void accessKeysPressed()
  {
    getViewHandler().openView("ManageAccessKey.fxml");
  }

}

