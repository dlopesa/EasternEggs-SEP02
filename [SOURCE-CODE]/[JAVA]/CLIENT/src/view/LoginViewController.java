package view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import viewmodel.LoginViewModel;

public class LoginViewController extends ViewController
{

  @FXML private Text accessKeyField;
  private LoginViewModel loginViewModel;

  @Override protected void init()
  {

    accessKeyField.textProperty().bind(loginViewModel.getAccessKeyProperty());
  }

  @FXML public void onEnter()
  {

    //if the password is correct :

      //MAYBE THESE SHOULD BE IN THE INIT METHOD? OR? Testing will tell.
      loginViewModel.login();
      getViewHandler().openView("AddItemView.fxml");
  }
}
