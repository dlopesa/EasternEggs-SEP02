package viewmodel;

import model.Model;

public class ViewModelFactory
{

  private CustomerViewModel customerViewModel;
  private AddItemViewModel addItemViewModel;

  public ViewModelFactory(Model model)
  {
    this.customerViewModel = new CustomerViewModel(model);
    this.addItemViewModel = new AddItemViewModel(model);
  }

  public CustomerViewModel getCustomerViewModel()
  {
    return customerViewModel;
  }

  public AddItemViewModel getAddItemViewModel()
  {
    return addItemViewModel;
  }
}
