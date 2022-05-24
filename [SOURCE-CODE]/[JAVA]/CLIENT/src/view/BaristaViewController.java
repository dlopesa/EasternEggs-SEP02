package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import property.OrderProperty;
import utility.Order;
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

    idCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
    timeCol.setCellValueFactory(new PropertyValueFactory<Order, String>("time"));
    priceCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
    statusCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("status"));
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
