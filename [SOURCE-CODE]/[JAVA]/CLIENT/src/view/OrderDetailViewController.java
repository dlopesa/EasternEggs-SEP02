package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import property.ItemProperty;
import utility.Item;
import utility.Order;
import viewmodel.BaristaViewModel;

public class OrderDetailViewController extends ViewController
{
  @FXML private Label titleLabel;
  @FXML private TableView itemsTable;
  @FXML private TableColumn idCol;
  @FXML private TableColumn nameCol;
  @FXML private TableColumn typeCol;
  @FXML private TableColumn priceCol;
  @FXML private TextArea commentArea;
  BaristaViewModel viewModel;

  public OrderDetailViewController()
  {

  }

  @Override protected void init()
  {
    this.viewModel = getViewModelFactory().getBaristaViewModel();

    idCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
    nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
    typeCol.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
    priceCol.setCellValueFactory(
        new PropertyValueFactory<Item, Double>("price"));
    reset();
  }

  public void reset()
  {
    itemsTable.setItems(viewModel.getSelectedOrder().getItemList());
    commentArea.setText(viewModel.getSelectedOrder().commentProperty().get());
  }

  @FXML void backPressed()
  {
    getViewHandler().openView("BaristaView.fxml");
  }

  @FXML void completePressed()
  {
    viewModel.completeOrder(viewModel.getSelectedOrder().getOrder());
    backPressed();
  }
}
