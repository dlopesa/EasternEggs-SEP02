package view;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import viewmodel.LoginViewModel;

public class LoginViewController extends ViewController
{

  @FXML private PasswordField passwordField;
  private LoginViewModel loginViewModel;

  @Override protected void init()
  {
    this.loginViewModel=getViewModelFactory().getLoginViewModel();
   
    loginViewModel.getAccessKeyProperty().bind(passwordField.textProperty());

  }

  @FXML public void onEnter()
  {
    getViewHandler().openView("DatabaseView.fxml");
  }

  @FXML public void back()
  {
    getViewHandler().openView("StartView.fxml");
  }


}
