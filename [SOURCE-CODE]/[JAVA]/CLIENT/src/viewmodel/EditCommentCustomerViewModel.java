package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class EditCommentCustomerViewModel
{
  private Model model;
  private StringProperty commentArea;

  public EditCommentCustomerViewModel(Model model) {
    this.model = model;
    this.commentArea = new SimpleStringProperty();
  }

  public void reset() {
    try
    {
      this.commentArea.set(model.getOrder().getComment());
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }

  public StringProperty getCommentArea()
  {
    return commentArea;
  }

  public void submit() {
    try
    {
      model.editOrderCommentByCustomer(commentArea.get());
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }
}
