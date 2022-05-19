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
    this.loginViewModel = getViewModelFactory().getLoginViewModel();

    loginViewModel.getAccessKeyProperty().bind(passwordField.textProperty());

  }

  @FXML private void onEnter()
  {
    getViewHandler().openView("DatabaseView.fxml");
  }

  @FXML private void back()
  {
    getViewHandler().openView("StartView.fxml");
  }

  @FXML private void barista()
  {
    getViewHandler().openView("BaristaView.fxml");
  }

  @FXML private void display()
  {
    getViewHandler().openView("DisplayView.fxml");
  }

}
