package view;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import viewmodel.AddExtraViewModel;

public class AddExtraViewController extends ViewController
{
  @FXML private TextField nameField;
  @FXML private CheckBox coffeeCheckbox;
  @FXML private CheckBox teaCheckbox;
  @FXML private CheckBox snackCheckbox;
  @FXML private CheckBox smoothieCheckbox;
  private AddExtraViewModel viewModel;


  @Override protected void init()
  {
    viewModel = getViewModelFactory().getAddExtraViewModel();
    viewModel.nameProperty().bind(nameField.textProperty());
    viewModel.coffeeProperty().bind(coffeeCheckbox.selectedProperty());
    viewModel.teaProperty().bind(teaCheckbox.selectedProperty());
    viewModel.snackProperty().bind(snackCheckbox.selectedProperty());
    viewModel.smoothieProperty().bind(smoothieCheckbox.selectedProperty());
  }

  @FXML private void backPressed() {
    getViewHandler().openView("ExtraDatabaseView.fxml");
  }

  @FXML private void addPressed() {
    viewModel.addExtra();
    backPressed();
  }


}
