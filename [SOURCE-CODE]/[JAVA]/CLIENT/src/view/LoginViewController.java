package view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import viewmodel.LoginViewModel;

public class LoginViewController extends ViewController
{

  @FXML private Text password;
  @FXML private ChoiceBox choiceBox;
  @FXML private Text userType;
  private LoginViewModel loginViewModel;

  @Override protected void init()
  {
    choiceBox.getItems().add("Admin");
    choiceBox.getItems().add("Barista");
    choiceBox.getItems().add("Cashier");
    password.textProperty().bind(loginViewModel.getPasswordProperty());
  }

  @FXML public void onEnter()
  {
      userType.textProperty().set(choiceBox.getValue().toString());
      userType.textProperty().bind(loginViewModel.getUserTypeProperty());
      //MAYBE THESE SHOULD BE IN THE INIT METHOD? OR? Testing will tell.
      loginViewModel.login();
  }
}
