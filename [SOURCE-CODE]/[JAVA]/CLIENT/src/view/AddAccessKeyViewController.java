package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewmodel.AddAccessKeyViewModel;
import viewmodel.AddItemViewModel;

public class AddAccessKeyViewController extends ViewController
{
  @FXML private TextField accessKey;
  @FXML private ChoiceBox<String> permissionChoiceBox;
  @FXML private Label errorLabel;
  private AddAccessKeyViewModel addAccessKeyViewModel;

  @Override protected void init()
  {
    addAccessKeyViewModel = getViewModelFactory().getAddAccessKeyViewModel();
    accessKey.textProperty().bindBidirectional(addAccessKeyViewModel.accessKeyProperty());
    errorLabel.textProperty()
        .bindBidirectional(addAccessKeyViewModel.errorProperty());

    //Putting items inside the choice box
    permissionChoiceBox.valueProperty()
        .bindBidirectional(addAccessKeyViewModel.chosenProperty());
    //Binding the chosen item with the view model
  }

  public void reset() {
    addAccessKeyViewModel.reset();
    permissionChoiceBox.getItems().clear();
    permissionChoiceBox.getItems().addAll(addAccessKeyViewModel.getPermission());
  }
  @FXML public void submitButton()
  {
    addAccessKeyViewModel.submit();
    getViewModelFactory().getManageAccessKeysViewModel().reset();
  }

  @FXML public void backButton()
  {
    getViewHandler().openView("ManageAccessKey.fxml");
    addAccessKeyViewModel.clear();
  }


}
