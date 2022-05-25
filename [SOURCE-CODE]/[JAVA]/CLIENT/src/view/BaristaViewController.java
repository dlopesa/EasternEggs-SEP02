package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import property.OrderProperty;
import viewmodel.BaristaViewModel;

import java.rmi.RemoteException;

public class BaristaViewController extends ViewController
{
  private @FXML TableView orderTableView;
  private @FXML TableColumn idCol;
  private @FXML TableColumn timeCol;
  private @FXML TableColumn priceCol;
  private @FXML TableColumn statusCol;
  private BaristaViewModel viewModel;
  private TableView.TableViewSelectionModel selectionModel;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getBaristaViewModel();

    idCol.setCellValueFactory(new PropertyValueFactory<OrderProperty, IntegerProperty>("id"));
    timeCol.setCellValueFactory(new PropertyValueFactory<OrderProperty, StringProperty>("time"));
    priceCol.setCellValueFactory(new PropertyValueFactory<OrderProperty, DoubleProperty>("price"));
    statusCol.setCellValueFactory(new PropertyValueFactory<OrderProperty, DoubleProperty>("status"));
    orderTableView.setItems(viewModel.getOrders());
    selectionModel = orderTableView.getSelectionModel();
  }

  public void reset() {
    viewModel.open();
    viewModel.reset();
  }

  @FXML private void seeDetailsPressed() {
    OrderProperty order = (OrderProperty) selectionModel.getSelectedItem();
    viewModel.setSelectedOrder(order);
    getViewHandler().openView("OrderDetailView.fxml");
    viewModel.close();
  }

  @FXML private void backPressed() {
    getViewHandler().openView("StartView.fxml");
    viewModel.close();
  }
}
