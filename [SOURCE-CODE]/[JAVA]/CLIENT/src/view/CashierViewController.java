package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import property.OrderProperty;
import viewmodel.CashierViewModel;

public class CashierViewController extends ViewController
{
  @FXML private TableView unpaidTable;
  @FXML private TableView pendingTable;
  @FXML private TabPane tabPane;
  private SelectionModel unpaidOrderSelection;
  private SelectionModel pendingOrderSelection;
  private CashierViewModel viewModel;

  @Override protected void init()
  {
    this.viewModel = getViewModelFactory().getCashierViewModel();
    tabPane.setTabMinWidth(130);
    tabPane.setTabMinHeight(22);
    reset();
    unpaidOrderSelection = unpaidTable.getSelectionModel();
    pendingOrderSelection = pendingTable.getSelectionModel();

  }

  public void reset() {
    viewModel.reset();
    setTable(unpaidTable, "unpaid");
    setTable(pendingTable, "pending");
  }

  private void setTable(TableView table, String status)
  {

    TableColumn idColTemp = (TableColumn) table.getColumns().get(0);
    TableColumn timeColTemp = (TableColumn) table.getColumns().get(1);
    TableColumn priceColTemp = (TableColumn) table.getColumns().get(2);
    TableColumn statusColTemp = (TableColumn) table.getColumns().get(3);
    idColTemp.setCellValueFactory(
        new PropertyValueFactory<OrderProperty, IntegerProperty>("id"));
    timeColTemp.setCellValueFactory(
        new PropertyValueFactory<OrderProperty, StringProperty>("time"));
    priceColTemp.setCellValueFactory(
        new PropertyValueFactory<OrderProperty, StringProperty>("price"));
    statusColTemp.setCellValueFactory(
        new PropertyValueFactory<OrderProperty, DoubleProperty>("status"));

    switch (status) {
      case "unpaid":
        table.setItems(viewModel.getUnpaidOrders());
        break;
      case "pending":
        table.setItems(viewModel.getPendingOrders());
        break;

    }
  }

  @FXML private void markAsPaidPressed() {
    OrderProperty order = (OrderProperty) unpaidOrderSelection.getSelectedItem();
    viewModel.setSelectedUnpaidOrder(order);
    viewModel.markOrderAsPaid();
    reset();
  }

  @FXML private void cancelPressed() {
    OrderProperty order = (OrderProperty) unpaidOrderSelection.getSelectedItem();
    viewModel.setSelectedUnpaidOrder(order);
    viewModel.cancelOrder();
    reset();
  }

  @FXML private void editCommentPressed() {
    OrderProperty order = (OrderProperty) pendingOrderSelection.getSelectedItem();
    viewModel.setSelectedPendingOrder(order);
    getViewModelFactory().getEditCommentCashierViewModel().reset();
    getViewHandler().openView("EditCommentCashierView.fxml");
  }

  @FXML private void backPressed() {
    getViewHandler().openView("LoginView.fxml");
  }
}
