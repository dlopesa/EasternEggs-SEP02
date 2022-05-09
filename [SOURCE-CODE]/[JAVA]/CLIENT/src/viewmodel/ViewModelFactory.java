package viewmodel;

import model.Model;

public class ViewModelFactory
{

  private CustomerViewModel customerViewModel;
  private StartViewModel startViewModel;
  private LoginViewModel loginViewModel;

  public ViewModelFactory(Model model)
  {
    this.customerViewModel = new CustomerViewModel(model);
    this.startViewModel = new StartViewModel(model);
    this.loginViewModel=new LoginViewModel(model);
  }

  public CustomerViewModel getCustomerViewModel()
  {
    return customerViewModel;
  }

  public StartViewModel getStartViewModel()
  {
    return startViewModel;
  }

  public LoginViewModel getLoginViewModel(){
    return loginViewModel;
  }
}
