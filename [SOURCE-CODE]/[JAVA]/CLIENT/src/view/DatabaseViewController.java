package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DatabaseViewController extends ViewController
{
  @FXML private TableView itemTable;
  @FXML private TableColumn idCol;
  @FXML private TableColumn nameCol;
  @FXML private TableColumn typeCol;
  @FXML private TableColumn priceCol;

  @Override protected void init()
  {

  }

  public void removeItemPressed(ActionEvent actionEvent)
  {
    //Todo future use case
  }

  public void editItemPressed(ActionEvent actionEvent)
  {
    //Todo future use case
  }

  public void backPressed(ActionEvent actionEvent)
  {
    getViewHandler().openView("LoginView.fxml");
  }

  public void addItemPressed(ActionEvent actionEvent)
  {
    getViewHandler().openView("AddItemView.fxml");
  }
}
