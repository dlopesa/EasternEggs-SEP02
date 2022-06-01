package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import property.ExtraInItemProperty;
import property.ItemProperty;
import utility.Item;
import viewmodel.OrderDetailViewModel;

public class OrderDetailViewController extends ViewController
{
  @FXML private Label titleLabel;
  @FXML private TableView<ItemProperty> itemsTable;
  @FXML private TableColumn<ItemProperty, IntegerProperty> idCol;
  @FXML private TableColumn<ItemProperty, StringProperty> nameCol;
  @FXML private TableColumn<ItemProperty, StringProperty> typeCol;
  @FXML private TableColumn<ItemProperty, DoubleProperty> priceCol;
  @FXML private TableView<ExtraInItemProperty> extrasTable;
  @FXML private TextArea commentArea;
  OrderDetailViewModel viewModel;

  public OrderDetailViewController()
  {
  }

  @Override protected void init()
  {
    this.viewModel = getViewModelFactory().getOrderDetailViewModel();

    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    TableColumn idColTemp = extrasTable.getColumns().get(0);
    TableColumn nameColTemp = extrasTable.getColumns().get(1);
    TableColumn extraColTemp = extrasTable.getColumns().get(2);
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
