package view;

import javafx.fxml.FXML;
import viewmodel.StartViewModel;

public class StartViewController extends ViewController
{

  private StartViewModel startViewModel;

  @Override protected void init()
  {
    this.startViewModel=getViewModelFactory().getStartViewModel();
  }

  @FXML private void orderButton(){
    getViewHandler().openView("CustomerView.fxml");
  }

  @FXML private void loginButton(){
    getViewHandler().openView("LoginView.fxml");
  }
}
