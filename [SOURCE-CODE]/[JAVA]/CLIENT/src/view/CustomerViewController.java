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
    setTable(itemTableCoffee, "coffee");
    setTable(itemTableSnack, "snack");
    setTable(itemTableTea, "tea");
    setTable(itemTableSmoothie, "smoothie");
  }

  private void setTable(TableView table, String type)
  {

    TableColumn idColTemp = (TableColumn) table.getColumns().get(0);
    TableColumn nameColTemp = (TableColumn) table.getColumns().get(1);
    TableColumn typeColTemp = (TableColumn) table.getColumns().get(2);
    TableColumn priceColTemp = (TableColumn) table.getColumns().get(3);
    idColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, Integer>("id"));
    nameColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, String>("name"));
    typeColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, String>("type"));
    priceColTemp.setCellValueFactory(
        new PropertyValueFactory<Item, Double>("price"));

    ObservableList<Item> observableListItem = FXCollections.observableArrayList(
        customerViewModel.getItemsByType(type));
    table.setItems(observableListItem);
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
