package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import model.UserProxy;
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
    switch (pwd)
    {
      case "Barista":
        loginViewModel.setUserType(UserProxy.BARISTA);
        getViewHandler().openView("BaristaView.fxml");

        break;
      case "Admin":
        loginViewModel.setUserType(UserProxy.ADMIN);
        getViewHandler().openView("AdminView.fxml");

        break;
      case "Cashier":
        loginViewModel.setUserType(UserProxy.CASHIER);
        getViewHandler().openView("CashierView.fxml");

        break;
      case "Display":
        loginViewModel.setUserType(UserProxy.DISPLAY);
        getViewHandler().openView("DisplayView.fxml");
        break;
      default:
        errorLabel.setVisible(true);
        break;
    }
  }

  @FXML private void back()
  {
    getViewHandler().openView("StartView.fxml");
  }

}
