package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewmodel.AddItemViewModel;

public class AddItemViewController extends ViewController
{
  @FXML private TextField nameField;
  @FXML private ChoiceBox<String> typeChoiceBox;
  @FXML private TextField priceField;
  @FXML private TextArea descriptionArea;
  @FXML private Label errorLabel;
  private StringProperty chosenProperty;
  private AddItemViewModel addItemViewModel;

  @Override protected void init()
  {
    addItemViewModel = getViewModelFactory().getAddItemViewModel();
    nameField.textProperty().bindBidirectional(addItemViewModel.nameProperty());
    priceField.textProperty()
        .bindBidirectional(addItemViewModel.priceProperty());
    descriptionArea.textProperty()
        .bindBidirectional(addItemViewModel.descriptionProperty());
    errorLabel.textProperty()
        .bindBidirectional(addItemViewModel.errorProperty());
    typeChoiceBox.getItems().addAll(addItemViewModel.getTypes());
  }

  @FXML private void submitButton()
  {
    //Another way to do it
    //typeChoiceBox.valueProperty();
    chosenProperty = new SimpleStringProperty(typeChoiceBox.getValue());
    chosenProperty.bindBidirectional(addItemViewModel.chosenProperty());
    addItemViewModel.submit();
    getViewHandler().openView("DatabaseView.fxml");
    addItemViewModel.clear();
  }

  @FXML private void backButton()
  {
    getViewHandler().openView("DatabaseView.fxml");
    addItemViewModel.clear();
  }
}
