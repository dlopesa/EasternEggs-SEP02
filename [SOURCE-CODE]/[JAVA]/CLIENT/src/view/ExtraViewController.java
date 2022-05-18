package view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import property.ExtraProperty;
import viewmodel.ExtraViewModel;

import java.util.ArrayList;

public class ExtraViewController extends ViewController
{
  @FXML Label itemName;
  @FXML private TableView extraTableCoffee;
  @FXML private TableView extraTableTea;
  @FXML private TableView extraTableSnack;
  @FXML private TableView extraTableSmoothie;
  @FXML private TabPane tabPane;
  private ExtraViewModel extraViewModel;

  @Override protected void init()
  {

    this.extraViewModel = getViewModelFactory().getExtraViewModel();
    itemName.textProperty().bind(extraViewModel.getNameProperty());
    tabPane.setTabMinWidth(130);
    tabPane.setTabMinHeight(22);
    reset();
  }

  public void reset()
  {
    extraViewModel.reset();

  }

  private void setTable(TableView table, String type)
  {
    TableColumn nameColTemp= (TableColumn) table.getColumns().get(0);
    TableColumn yesNoTemp= (TableColumn) table.getColumns().get(1);
    nameColTemp.setCellValueFactory(new PropertyValueFactory<ExtraProperty, StringProperty>("Extra"));

    table.setItems(extraViewModel.getExtrasByType(type));
  }

  @FXML private void onDone()
  {
    getViewHandler().openView("CustomerView.fxml");
  }
}
