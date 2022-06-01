package view;

import javafx.fxml.FXML;
import model.UserProxy;
import viewmodel.StartViewModel;

public class StartViewController extends ViewController
{

  private StartViewModel startViewModel;

  @Override protected void init()
  {
    this.startViewModel=getViewModelFactory().getStartViewModel();
  }

  @FXML private void orderButton(){
    startViewModel.setUserType(UserProxy.CUSTOMER);
    getViewHandler().openView("CustomerView.fxml");
  }

  @FXML private void loginButton(){
    getViewHandler().openView("LoginView.fxml");
  }
}
