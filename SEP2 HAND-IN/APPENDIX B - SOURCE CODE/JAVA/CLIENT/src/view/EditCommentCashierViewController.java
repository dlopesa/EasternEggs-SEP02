package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import viewmodel.EditCommentCashierViewModel;

public class EditCommentCashierViewController extends ViewController
{
  @FXML private Label orderNumberLabel;
  @FXML private TextArea commentTextArea;
  private EditCommentCashierViewModel viewModel;


  @Override protected void init()
  {
    viewModel = getViewModelFactory().getEditCommentCashierViewModel();
    orderNumberLabel.textProperty().bind(viewModel.getOrderNumber());
    commentTextArea.textProperty().bindBidirectional(viewModel.getCommentProperty());
  }

  @FXML private void cancelPressed() {
    getViewHandler().openView("CashierView.fxml");
  }

  @FXML private void confirmPressed() {
    viewModel.editComment();
    cancelPressed();
  }
}
