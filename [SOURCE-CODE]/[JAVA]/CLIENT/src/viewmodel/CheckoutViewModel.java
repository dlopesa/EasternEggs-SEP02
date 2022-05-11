package viewmodel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.*;
import utility.Item;

import java.util.ArrayList;

public class CheckoutViewModel
{
  private Model model;
  private StringProperty totalPrice;
  private StringProperty error;


  public CheckoutViewModel(Model model)
  {
    this.model=model;
    totalPrice=new SimpleStringProperty();
    double tempPrice = model.getOrder().getPrice();
    totalPrice.set(String.valueOf(tempPrice));
    error = new SimpleStringProperty("");
  }

  public ArrayList<Item> getItemsInOrder()
  {
    return model.getOrder().getItemList().getAllItems();
  }

  public StringProperty getTotalPrice()
  {
    return totalPrice;
  }

  public StringProperty getError()
  {
    return error;
  }


}
