package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import property.ExtraInItemProperty;
import property.ExtraProperty;
import property.ItemProperty;
import utility.Item;
import utility.Order;
import viewmodel.BaristaViewModel;
import viewmodel.OrderDetailViewModel;

import java.util.ArrayList;

public class OrderDetailViewController extends ViewController
{
  @FXML private Label titleLabel;
  @FXML private TableView itemsTable;
  @FXML private TableColumn idCol;
  @FXML private TableColumn nameCol;
  @FXML private TableColumn typeCol;
  @FXML private TableColumn priceCol;
  @FXML private TableView extrasTable;
  @FXML private TextArea commentArea;
  OrderDetailViewModel viewModel;

  public OrderDetailViewController()
  {
  }

  @Override protected void init()
  {
    this.viewModel = getViewModelFactory().getOrderDetailViewModel();

    idCol.setCellValueFactory(new PropertyValueFactory<ItemProperty, IntegerProperty>("id"));
    nameCol.setCellValueFactory(new PropertyValueFactory<ItemProperty, StringProperty>("name"));
    typeCol.setCellValueFactory(new PropertyValueFactory<ItemProperty, StringProperty>("type"));
    priceCol.setCellValueFactory(
        new PropertyValueFactory<ItemProperty, DoubleProperty>("price"));

    TableColumn idColTemp = (TableColumn) extrasTable.getColumns().get(0);
    TableColumn nameColTemp = (TableColumn) extrasTable.getColumns().get(1);
    TableColumn extraColTemp = (TableColumn) extrasTable.getColumns().get(2);

    idColTemp.setCellValueFactory(
        new PropertyValueFactory<ExtraInItemProperty, Integer>("id"));
    nameColTemp.setCellValueFactory(
        new PropertyValueFactory<ExtraInItemProperty, String>("name"));
    extraColTemp.setCellValueFactory(
        new PropertyValueFactory<ExtraInItemProperty, String>("ExtrasString"));
    reset();
  }

  public void reset()
  {
    viewModel.reset();
    extrasTable.setItems(viewModel.getExtrasInItems());
    itemsTable.setItems(viewModel.getSelectedOrder().getItemList());
    commentArea.setText(viewModel.getSelectedOrder().commentProperty().get());
  }

  @FXML void backPressed()
  {
    getViewHandler().openView("BaristaView.fxml");
  }

  @FXML void completePressed()
  {
    viewModel.completeOrder();
    backPressed();
  }
}
