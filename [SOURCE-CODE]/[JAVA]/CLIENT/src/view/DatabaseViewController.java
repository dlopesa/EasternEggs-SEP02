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
  }

  public void reset()
  {
    viewModel.reset();
    itemTable.setItems(viewModel.getAllItems());
  }

  @FXML public void removeItemPressed()
  {
    ItemProperty item = (ItemProperty) itemTable.getSelectionModel().getSelectedItem();
    viewModel.removeItem(item);

    reset();
  }

  @FXML public void editItemPressed()
  {

  }

  @FXML public void backPressed()
  {
    getViewHandler().openView("AdminView.fxml");
  }

  @FXML public void addItemPressed()
  {
    getViewHandler().openView("AddItemView.fxml");
  }

}

