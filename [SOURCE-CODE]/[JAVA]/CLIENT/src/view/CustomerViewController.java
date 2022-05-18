package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import property.ItemProperty;
import viewmodel.CustomerViewModel;

import java.util.Optional;

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
        new PropertyValueFactory<ItemProperty, IntegerProperty>("id"));
    nameColTemp.setCellValueFactory(
        new PropertyValueFactory<ItemProperty, StringProperty>("name"));
    typeColTemp.setCellValueFactory(
        new PropertyValueFactory<ItemProperty, StringProperty>("type"));
    priceColTemp.setCellValueFactory(
        new PropertyValueFactory<ItemProperty, DoubleProperty>("price"));

    table.setItems(customerViewModel.getItemsByType(type));
  }

  private ItemProperty getItemProperty()
  {
    int indexOfTab = tabPane.getSelectionModel().getSelectedIndex();
    ItemProperty item = null;
    switch (indexOfTab)
    {
      case 0:
        item = (ItemProperty) itemTableCoffee.getSelectionModel()
            .getSelectedItem();
        break;
      case 1:
        item = (ItemProperty) itemTableTea.getSelectionModel()
            .getSelectedItem();
        break;
      case 2:
        item = (ItemProperty) itemTableSnack.getSelectionModel()
            .getSelectedItem();
        break;
      case 3:
        item = (ItemProperty) itemTableSmoothie.getSelectionModel()
            .getSelectedItem();
        break;
    }
    return item;
  }

  private boolean confirmation()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Addition of extras");
    alert.setHeaderText("Would you like to add any extras to your "+getItemProperty().getItem().getName()+" ?");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  @FXML private void addToOrderButton()
  {

    ItemProperty item = getItemProperty();
    if (confirmation())
    {
      customerViewModel.setItemForExtras(item);
      getViewHandler().openView("ExtraView.fxml");
    }
    else
    {
      customerViewModel.addToOrder(item);
    }
  }

  @FXML private void descriptionButton()
  {
    ItemProperty item = getItemProperty();
    customerViewModel.seeDescription(item);
    getViewHandler().openView("DescriptionView.fxml");
  }

  @FXML private void checkoutButton()
  {
    getViewHandler().openView("CheckoutView.fxml");
  }

  @FXML private void quitButton()
  {
    customerViewModel.quit();
    getViewHandler().openView("StartView.fxml");
  }
}
