package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import viewmodel.CheckoutViewModel;

public class CheckoutViewController extends ViewController
{
  @FXML private TableView itemTable;
  @FXML private Label totalPriceLabel;
  @FXML private Label errorLabel;
  private CheckoutViewModel checkoutViewModel;

  @Override protected void init()
  {
    this.checkoutViewModel=getViewModelFactory().getCheckoutViewModel();
    totalPriceLabel.textProperty().bind(checkoutViewModel.getTotalPrice());
    errorLabel.textProperty().bind(checkoutViewModel.getError());

  }

  @FXML private void removeFromOrderButton()
  {

  }

  @FXML private void payButton()
  {

  }

  @FXML private void quitButton()
  {

  }
}
