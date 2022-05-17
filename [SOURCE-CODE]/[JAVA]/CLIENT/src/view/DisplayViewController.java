package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import property.ItemProperty;
import property.OrderProperty;
import viewmodel.DisplayViewModel;

public class DisplayViewController extends ViewController
{
  @FXML private TableView<OrderProperty> tableViewCompleted;
  @FXML private TableView<OrderProperty> tableViewPending;
  private DisplayViewModel displayViewModel;

  @Override protected void init()
  {
    displayViewModel = getViewModelFactory().getDisplayViewModel();
    setColumns(tableViewCompleted);
    setColumns(tableViewPending);
    reset();
  }

  public void reset()
  {
    displayViewModel.reset();
    tableViewPending.setItems(displayViewModel.getPendingList());
    tableViewCompleted.setItems(displayViewModel.getCompletedList());
  }

  public void setColumns(TableView table)
  {
    TableColumn idColTemp = (TableColumn) table.getColumns().get(0);
    TableColumn nameColTemp = (TableColumn) table.getColumns().get(1);
    idColTemp.setCellValueFactory(
        new PropertyValueFactory<OrderProperty, IntegerProperty>("id"));
    nameColTemp.setCellValueFactory(
        new PropertyValueFactory<OrderProperty, StringProperty>("status"));
  }

  @FXML private void back()
  {
    getViewHandler().openView("LoginView.fxml");
  }
}
