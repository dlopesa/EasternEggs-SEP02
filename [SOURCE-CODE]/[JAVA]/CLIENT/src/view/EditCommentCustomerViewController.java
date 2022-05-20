package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import viewmodel.EditCommentCashierViewModel;
import viewmodel.EditCommentCustomerViewModel;

public class EditCommentCustomerViewController extends ViewController
{
  @FXML private TextArea commentTextArea;
  private EditCommentCustomerViewModel viewModel;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getEditCommentCustomerViewModel();
    commentTextArea.textProperty()
        .bindBidirectional(viewModel.getCommentArea());
  }

  @FXML private void cancelPressed()
  {
    getViewHandler().openView("CheckoutView.fxml");
  }

  @FXML private void confirmPressed()
  {
    viewModel.submit();
    cancelPressed();
  }
}
