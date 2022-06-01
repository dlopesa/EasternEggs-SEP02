package viewmodel;

import model.Model;

public class PaymentViewModel
{
  private Model model;

  public PaymentViewModel(Model model)
  {
    this.model = model;
  }

  public int payWithCash()
  {
    try
    {
      return model.payForOrder(true);
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
    return -100;
  }

  public int payWithCardOrMP()
  {
    try
    {
      return model.payForOrder(false);
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
    return -100;
  }

  public void setIsTakeAway(){
    try
    {
      model.setIsTakeAway();
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }
}
