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
    return model.payForOrder(true);
  }

  public int payWithCardOrMP()
  {
    return model.payForOrder(false);
  }

  public void setIsTakeAway(){
    model.setIsTakeAway();
  }
}
