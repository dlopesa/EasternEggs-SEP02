package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import utility.Item;
import viewmodel.CustomerViewModel;

public class CustomerViewController extends ViewController
{
  @FXML private TableView itemTableCoffee;
  @FXML private TableView itemTableTea;
  @FXML private TableView itemTableSnack;
  @FXML private TableView itemTableSmoothie;
  @FXML private Label errorLabel;
  @FXML private TabPane tabPane;
  private CustomerViewModel customerViewModel;

  @Override protected void init()
  {
    this.customerViewModel = getViewModelFactory().getCustomerViewModel();
    tabPane.setTabMinWidth(130);
    tabPane.setTabMinHeight(22);
    //Alignment of tabs
    errorLabel.textProperty().bind(customerViewModel.getErrorProperty());
    reset();
  }

  public void reset()
  {
    customerViewModel.reset();
    coffeeTable();
    teaTable();
    snackTable();
    smoothieTable();
  }

  private void coffeeTable()
  {
    TableColumn idColTemp = (TableColumn) itemTableCoffee.getColumns().get(0);
    TableColumn nameColTemp = (TableColumn) itemTableCoffee.getColumns().get(1);
    TableColumn typeColTemp = (TableColumn) itemTableCoffee.getColumns().get(2);
    TableColumn priceColTemp = (TableColumn) itemTableCoffee.getColumns()
        .get(3);
    idColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, Integer>("id"));
    nameColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, String>("name"));
    typeColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, String>("type"));
    priceColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, Double>("price"));

    ObservableList<Item> observableListItem = FXCollections.observableArrayList(
        customerViewModel.getItemsByType("coffee"));
    itemTableCoffee.setItems(observableListItem);
  }

  private void teaTable()
  {
    TableColumn idColTemp = (TableColumn) itemTableTea.getColumns().get(0);
    TableColumn nameColTemp = (TableColumn) itemTableTea.getColumns().get(1);
    TableColumn typeColTemp = (TableColumn) itemTableTea.getColumns().get(2);
    TableColumn priceColTemp = (TableColumn) itemTableTea.getColumns().get(3);
    idColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, Integer>("id"));
    nameColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, String>("name"));
    typeColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, String>("type"));
    priceColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, Double>("price"));

    ObservableList<Item> observableListItem = FXCollections.observableArrayList(
        customerViewModel.getItemsByType("tea"));
    itemTableTea.setItems(observableListItem);
  }

  private void snackTable()
  {
    TableColumn idColTemp = (TableColumn) itemTableSnack.getColumns().get(0);
    TableColumn nameColTemp = (TableColumn) itemTableSnack.getColumns().get(1);
    TableColumn typeColTemp = (TableColumn) itemTableSnack.getColumns().get(2);
    TableColumn priceColTemp = (TableColumn) itemTableSnack.getColumns().get(3);
    idColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, Integer>("id"));
    nameColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, String>("name"));
    typeColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, String>("type"));
    priceColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, Double>("price"));

    ObservableList<Item> observableListItem = FXCollections.observableArrayList(
        customerViewModel.getItemsByType("snack"));
    itemTableSnack.setItems(observableListItem);
  }

  private void smoothieTable()
  {
    TableColumn idColTemp = (TableColumn) itemTableSmoothie.getColumns().get(0);
    TableColumn nameColTemp = (TableColumn) itemTableSmoothie.getColumns()
        .get(1);
    TableColumn typeColTemp = (TableColumn) itemTableSmoothie.getColumns()
        .get(2);
    TableColumn priceColTemp = (TableColumn) itemTableSmoothie.getColumns()
        .get(3);
    idColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, Integer>("id"));
    nameColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, String>("name"));
    typeColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, String>("type"));
    priceColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, Double>("price"));

    ObservableList<Item> observableListItem = FXCollections.observableArrayList(
        customerViewModel.getItemsByType("smoothie"));
    itemTableSmoothie.setItems(observableListItem);
  }

  @FXML private void addToOrderButton()
  {
    int indexOfTab = tabPane.getSelectionModel().getSelectedIndex();
    Item item = null;
    switch (indexOfTab)
    {
      case 0:
        item = (Item) itemTableCoffee.getSelectionModel().getSelectedItem();
        break;
      case 1:
        item = (Item) itemTableTea.getSelectionModel().getSelectedItem();
        break;
      case 2:
        item = (Item) itemTableSnack.getSelectionModel().getSelectedItem();
        break;
      case 3:
        item = (Item) itemTableSmoothie.getSelectionModel().getSelectedItem();
        break;
    }
    customerViewModel.addToOrder(item);
  }

  @FXML private void checkoutButton()
  {
    getViewHandler().openView("CheckoutView.fxml");
  }

  @FXML private void quitButton()
  {
    getViewHandler().openView("StartView.fxml");
  }
}
