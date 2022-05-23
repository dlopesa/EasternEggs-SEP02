package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import property.ExtraProperty;
import property.ItemProperty;
import viewmodel.ExtraDatabaseViewModel;

public class ExtraDatabaseViewController extends ViewController
{
  @FXML private TableView extraTable;
  @FXML private TableColumn extraCol;
  @FXML private TableColumn availableCol;
  private ExtraDatabaseViewModel viewModel;
  private SelectionModel selectionModel;


  @Override protected void init()
  {
    viewModel = getViewModelFactory().getExtraDatabaseViewModel();
    extraCol.setCellValueFactory(new PropertyValueFactory<ExtraProperty, StringProperty>("name"));
    availableCol.setCellValueFactory(new PropertyValueFactory<ExtraProperty, String>("Types"));
    selectionModel = extraTable.getSelectionModel();
  reset();
  }

  public void reset() {
    viewModel.reset();
    extraTable.setItems(viewModel.getAllExtras());
  }

  @FXML private void addExtraPressed() {
    getViewHandler().openView("AddExtraView.fxml");
  }

  @FXML private void removeExtraPressed() {
    ExtraProperty selectedExtra = (ExtraProperty) selectionModel.getSelectedItem();
    viewModel.setSelectedExtra(selectedExtra);
    viewModel.removeExtra();
    reset();
  }

  @FXML private void backPressed() {
    getViewHandler().openView("AdminView.fxml");

  }
}
