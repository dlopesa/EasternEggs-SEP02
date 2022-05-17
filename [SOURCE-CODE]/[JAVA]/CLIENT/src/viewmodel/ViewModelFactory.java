package viewmodel;

import model.Model;

public class ViewModelFactory
{

  private CustomerViewModel customerViewModel;
  private StartViewModel startViewModel;
  private LoginViewModel loginViewModel;
  private AddItemViewModel addItemViewModel;
  private DatabaseViewModel databaseViewModel;
  private BaristaViewModel baristaViewModel;
  private CheckoutViewModel checkoutViewModel;
  private DescriptionViewModel descriptionViewModel;
  private DisplayViewModel displayViewModel;

  public ViewModelFactory(Model model)
  {
    this.startViewModel = new StartViewModel(model);
    this.loginViewModel = new LoginViewModel(model);
    this.addItemViewModel = new AddItemViewModel(model);
    this.databaseViewModel = new DatabaseViewModel(model);
    this.baristaViewModel = new BaristaViewModel(model);
    this.checkoutViewModel = new CheckoutViewModel(model);
    this.descriptionViewModel = new DescriptionViewModel(model);
    this.customerViewModel = new CustomerViewModel(model, descriptionViewModel);
    this.displayViewModel = new DisplayViewModel(model);
  }

  public CustomerViewModel getCustomerViewModel()
  {
    return customerViewModel;
  }

  public StartViewModel getStartViewModel()
  {
    return startViewModel;
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }

  public AddItemViewModel getAddItemViewModel()
  {
    return addItemViewModel;
  }

  public DatabaseViewModel getDatabaseViewModel()
  {
    return databaseViewModel;
  }

  public BaristaViewModel getBaristaViewModel()
  {
    return baristaViewModel;
  }

  public DescriptionViewModel getDescriptionViewModel()
  {
    return descriptionViewModel;
  }

  public CheckoutViewModel getCheckoutViewModel()
  {
    return checkoutViewModel;
  }

  public DisplayViewModel getDisplayViewModel()
  {
    return displayViewModel;
  }
}
