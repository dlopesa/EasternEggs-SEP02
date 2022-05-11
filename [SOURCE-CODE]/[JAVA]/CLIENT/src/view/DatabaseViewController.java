package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    TableColumn idColTemp = (TableColumn) itemTable.getColumns().get(0);
    TableColumn nameColTemp = (TableColumn) itemTable.getColumns().get(1);
    TableColumn typeColTemp = (TableColumn) itemTable.getColumns().get(2);
    TableColumn priceColTemp = (TableColumn) itemTable.getColumns().get(3);
    idColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, Integer>("id"));
    nameColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, String>("name"));
    typeColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, String>("type"));
    priceColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, Double>("price"));
    /*
    idCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
    nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
    typeCol.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
    priceCol.setCellValueFactory(
        new PropertyValueFactory<Item, Double>("price"));
     */

    ObservableList<Item> observableListItem = FXCollections.observableArrayList(
        viewModel.getAllItems());

    itemTable.setItems(observableListItem);
  }

  public void reset()
  {
    viewModel.reset();
    ObservableList<Item> observableListItem = FXCollections.observableArrayList(
        viewModel.getAllItems());
    itemTable.setItems(observableListItem);
  }

  @FXML public void removeItemPressed()
  {
    //Todo future use case
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

}

