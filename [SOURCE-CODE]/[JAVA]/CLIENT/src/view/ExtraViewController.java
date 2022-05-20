package view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import property.ExtraProperty;
import property.ItemProperty;
import viewmodel.ExtraViewModel;

import java.util.ArrayList;

public class ExtraViewController extends ViewController
{
  @FXML Label itemName;
  @FXML private TableView availableExtras;
  @FXML private TableView addedExtras;
  private ExtraViewModel extraViewModel;

  @Override protected void init()
  {

    this.extraViewModel = getViewModelFactory().getExtraViewModel();
    reset();
    itemName.textProperty().bind(extraViewModel.getNameProperty());
  }

  public void reset()
  {
    extraViewModel.reset();
    setTable();
    itemName.textProperty().bind(extraViewModel.getNameProperty());
  }

  private void setTable()
  {
    TableColumn nameColTemp = (TableColumn) availableExtras.getColumns().get(0);
    nameColTemp.setCellValueFactory(
        new PropertyValueFactory<ExtraProperty, StringProperty>("name"));
    availableExtras.setItems(extraViewModel.getAvailableExtras());
    TableColumn addedExtrasColumn = (TableColumn) addedExtras.getColumns().get(0);
    addedExtrasColumn.setCellValueFactory(
        new PropertyValueFactory<ExtraProperty, StringProperty>("name"));
    addedExtras.setItems(extraViewModel.getAddedExtras());
  }

  @FXML private void onDone()
  {
    extraViewModel.addItemToOrder();
    getViewHandler().openView("CustomerView.fxml");
  }

  @FXML private void onAdd()
  {

    ExtraProperty extra = (ExtraProperty) availableExtras.getSelectionModel().getSelectedItem();
    extraViewModel.addExtraToItem(extra);
  }

  @FXML private void onRemove()
  {
    ExtraProperty extra = (ExtraProperty) addedExtras.getSelectionModel().getSelectedItem();
    extraViewModel.removeExtraFromItem(extra);
  }
}
