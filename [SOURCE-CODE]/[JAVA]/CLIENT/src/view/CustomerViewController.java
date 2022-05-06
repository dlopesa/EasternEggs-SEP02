package view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import viewmodel.CustomerViewModel;

public class CustomerViewController extends ViewController
{
  @FXML private Text name;
  @FXML private Text price;
  @FXML private Text extra;
  @FXML private Text error;
  private CustomerViewModel customerViewModel;

  @Override protected void init()
  {
    this.customerViewModel = getViewModelFactory().getCustomerViewModel();
    name.textProperty().bind(customerViewModel.getNameProperty());
    price.textProperty().bind((customerViewModel.getPriceProperty()));
    extra.textProperty().bind(customerViewModel.getExtraProperty());
    error.textProperty().bind(customerViewModel.getErrorProperty());
  }

  @FXML private void submitButton()
  {
    customerViewModel.submitOrder();
  }

  @FXML private void addButton()
  {
    customerViewModel.addItemToOrder();
  }
}
