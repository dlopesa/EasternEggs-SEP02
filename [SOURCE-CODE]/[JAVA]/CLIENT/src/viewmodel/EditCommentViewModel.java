package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import utility.Order;
import view.CashierViewController;

public class EditCommentViewModel
{
  private Model model;
  private StringProperty orderNumber;
  private StringProperty commentArea;
  private CashierHandler handler;

  public EditCommentViewModel(Model model, CashierHandler handler) {
    this.model = model;
    this.handler = handler;

    this.orderNumber = new SimpleStringProperty();
    this.commentArea = new SimpleStringProperty();
  }

  public void reset() {
    this.orderNumber.set(String.valueOf(handler.getSelectedPendingOrder().getOrder().getId()));
    this.commentArea.set(handler.getSelectedPendingOrder().commentProperty().get());
  }

  public StringProperty getOrderNumber() {
    return orderNumber;
  }

  public StringProperty getCommentProperty() {
    return commentArea;
  }

  public void editComment() {
    Order order = handler.getSelectedPendingOrder().getOrder();
    model.editOrderComment(order,commentArea.get());
  }

}
