package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.Item;
import viewmodel.CheckoutViewModel;

public class CheckoutViewController extends ViewController
{
  @FXML private TableView itemTable;
  @FXML private Label totalPriceLabel;
  @FXML private Label errorLabel;
  private CheckoutViewModel checkoutViewModel;

  @Override protected void init()
  {
    this.checkoutViewModel=getViewModelFactory().getCheckoutViewModel();
    totalPriceLabel.textProperty().bind(checkoutViewModel.getTotalPrice());
    errorLabel.textProperty().bind(checkoutViewModel.getError());

    orderTable();

  }

  private void orderTable()
  {
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

    ObservableList<Item> observableListItem = FXCollections.observableArrayList(
        checkoutViewModel.getItemsInOrder());
    itemTable.setItems(observableListItem);
  }

  @FXML private void removeFromOrderButton()
  {
    Item item = (Item) itemTable.getSelectionModel().getSelectedItem();
    checkoutViewModel.removeFromOrder(item);
  }

  @FXML private void payButton()
  {
    //getViewHandler().openView(pay);
  }

  @FXML private void backButton()
  {
    getViewHandler().openView("CustomerView.fxml");
  }

  @FXML private void quitButton()
  {
    getViewHandler().openView("StartView.fxml");
  }
}
