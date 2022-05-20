package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import utility.Order;

public class EditCommentCustomerViewModel
{
  private Model model;
  private StringProperty commentArea;

  public EditCommentCustomerViewModel(Model model) {
    this.model = model;
    this.commentArea = new SimpleStringProperty();
  }

  public void reset() {
    this.commentArea.set("");
  }

  public StringProperty getCommentArea()
  {
    return commentArea;
  }

  public void submit() {
    model.editOrderComment(model.getOrder(),commentArea.get());
  }
}
