package view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewmodel.AddItemViewModel;
import viewmodel.DescriptionViewModel;

public class DescriptionViewController extends ViewController
{
  @FXML private TextField nameField;
  @FXML private ChoiceBox<String> typeChoiceBox;
  @FXML private TextField priceField;
  @FXML private TextArea descriptionArea;
  @FXML private Label errorLabel;
  private DescriptionViewModel descriptionViewModel;

  @Override protected void init()
  {
    descriptionViewModel = getViewModelFactory().getDescriptionViewModel();
    nameField.textProperty()
        .bindBidirectional(descriptionViewModel.nameProperty());
    priceField.textProperty()
        .bindBidirectional(descriptionViewModel.priceProperty());
    descriptionArea.textProperty()
        .bindBidirectional(descriptionViewModel.descriptionProperty());
    errorLabel.textProperty()
        .bindBidirectional(descriptionViewModel.errorProperty());
    typeChoiceBox.valueProperty()
        .bindBidirectional(descriptionViewModel.chosenProperty());
  }

  public void reset()
  {
    descriptionViewModel.reset();
  }

  @FXML private void backButton()
  {
    getViewHandler().openView("CustomerView.fxml");
    descriptionViewModel.reset();
  }
}
