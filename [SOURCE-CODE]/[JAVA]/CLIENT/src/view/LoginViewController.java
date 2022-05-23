package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import viewmodel.LoginViewModel;

import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

public class LoginViewController extends ViewController
{

  @FXML private PasswordField passwordField;
  @FXML private Label errorLabel;
  private LoginViewModel loginViewModel;

  @Override protected void init()
  {
    this.loginViewModel = getViewModelFactory().getLoginViewModel();

    loginViewModel.getAccessKeyProperty().bind(passwordField.textProperty());

  }

  @FXML private void onEnter()
  {
    String pwd = loginViewModel.getUserType();
    if (pwd.equals("Barista"))
    {
      getViewHandler().openView("BaristaView.fxml");
    }
    else if (pwd.equals("Admin"))
    {
      getViewHandler().openView("DatabaseView.fxml");
    }
    else if (pwd.equals("Cashier"))
    {
      getViewHandler().openView("CashierView.fxml");
    }
    else if (pwd.equals("Display"))
    {
      getViewHandler().openView("DisplayView.fxml");
    }
    else
    {
      errorLabel.setVisible(true);
    }
  }
  @FXML private void back()
  {
    getViewHandler().openView("StartView.fxml");
  }

}
