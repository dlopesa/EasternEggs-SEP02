package view;

import javafx.fxml.FXML;

public class AdminViewController extends ViewController
{
  @Override protected void init()
  {
    //nothin lol
  }

  @FXML public void itemsPressed() {
    getViewHandler().openView("DatabaseView.fxml");
  }
  @FXML public void extrasPressed() {
    getViewHandler().openView("ExtraDatabaseView.fxml");
  }
  @FXML public void accessKeysPressed() {
    getViewHandler().openView("ManageAccessKey.fxml");
  }
  @FXML public void backPressed() {
    getViewHandler().openView("LoginView.fxml");
  }

}
