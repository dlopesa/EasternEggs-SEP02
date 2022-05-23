package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import viewmodel.PaymentViewModel;

import java.util.Optional;

public class PaymentViewController extends ViewController
{

  private PaymentViewModel paymentViewModel;

  @FXML CheckBox takeAwayCheckbox;

  @Override protected void init()
  {
    this.paymentViewModel = getViewModelFactory().getPaymentViewModel();
    takeAwayCheckbox.setIndeterminate(false);
    takeAwayCheckbox.setSelected(false);

  }

  @FXML private void onCard()
  {
    if (isTakeAway())
    {
      paymentViewModel.setIsTakeAway();
    }

    orderNumberMessageNonCash(paymentViewModel.payWithCardOrMP());

  }

  @FXML private void onMobilePay()
  {
    if (isTakeAway())
    {
      paymentViewModel.setIsTakeAway();
    }
    orderNumberMessageNonCash(paymentViewModel.payWithCardOrMP());
  }

  @FXML private void onCash()
  {
    if (isTakeAway())
    {
      paymentViewModel.setIsTakeAway();
    }
    orderNumberMessageCash(paymentViewModel.payWithCash());
  }

  public boolean isTakeAway()
  {
    return takeAwayCheckbox.selectedProperty().get();
  }

  private void orderNumberMessageNonCash(int id)
  {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Order "+id);
    alert.setHeaderText("Your order is being prepared: Order Number #"+ id);
    alert.showAndWait();
    getViewHandler().openView("StartView.fxml");
    takeAwayCheckbox.setSelected(false);
  }

  private void orderNumberMessageCash(int id)
  {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Order #"+id);
    alert.setHeaderText("Please go to the cashier to pay for your order: Order Number #"+ id);
    Optional<ButtonType> result = alert.showAndWait();
    getViewHandler().openView("StartView.fxml");
    takeAwayCheckbox.setSelected(false);
  }
}
