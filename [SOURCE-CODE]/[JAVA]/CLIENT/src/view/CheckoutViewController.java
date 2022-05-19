package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.*;
import property.ItemProperty;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import viewmodel.CheckoutViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

public class CheckoutViewController extends ViewController
{
  @FXML private TableView<ItemProperty> itemTable;
  @FXML private Label totalPriceLabel;
  @FXML private Label errorLabel;
  private CheckoutViewModel checkoutViewModel;

  @Override protected void init()
  {
    this.checkoutViewModel = getViewModelFactory().getCheckoutViewModel();
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
        new PropertyValueFactory<ItemProperty, IntegerProperty>("id"));
    nameColTemp.setCellValueFactory(
        new PropertyValueFactory<ItemProperty, StringProperty>("name"));
    typeColTemp.setCellValueFactory(
        new PropertyValueFactory<ItemProperty, StringProperty>("type"));
    priceColTemp.setCellValueFactory(
        new PropertyValueFactory<ItemProperty, DoubleProperty>("price"));
    itemTable.setItems(checkoutViewModel.getItemsInOrder());
  }

  private void orderNumberMessageNonCash(int id)
  {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Order "+id);
      alert.setHeaderText("Your order is being prepared: Order Number #"+ id);
      alert.showAndWait();
  }

  private void orderNumberMessageCash(int id)
  {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Order #"+id);
    alert.setHeaderText("Please go to the cashier to pay for your order: Order Number #"+ id);
    Optional<ButtonType> result = alert.showAndWait();
  }

  @FXML private void removeFromOrderButton()
  {
    ItemProperty item = (ItemProperty) itemTable.getSelectionModel()
        .getSelectedItem();
    checkoutViewModel.removeFromOrder(item);
  }

  @FXML private void payButton()
  {
    orderNumberMessageNonCash(checkoutViewModel.submit());
    getViewHandler().openView("StartView.fxml");
    //getViewHandler().openView(pay);

  }

  @FXML private void backButton()
  {
    getViewHandler().openView("CustomerView.fxml");
  }

  @FXML private void quitButton()
  {
    checkoutViewModel.quit();
    getViewHandler().openView("StartView.fxml");
  }

  @FXML private void addCommentButton()
  {
  }
}
