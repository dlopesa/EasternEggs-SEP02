package viewmodel;

import model.Model;
import utility.Order;

public class ViewModelFactory
{

  private CustomerViewModel customerViewModel;
  private StartViewModel startViewModel;
  private LoginViewModel loginViewModel;
  private AddItemViewModel addItemViewModel;
  private DatabaseViewModel databaseViewModel;
  private BaristaHandler baristaHandler;
  private BaristaViewModel baristaViewModel;
  private OrderDetailViewModel orderDetailViewModel;
  private CheckoutViewModel checkoutViewModel;
  private DescriptionViewModel descriptionViewModel;
  private CashierViewModel cashierViewModel;
  private EditCommentViewModel editCommentViewModel;
  private CashierHandler cashierHandler;

  public ViewModelFactory(Model model)
  {
    this.startViewModel = new StartViewModel(model);
    this.loginViewModel = new LoginViewModel(model);
    this.addItemViewModel = new AddItemViewModel(model);
    this.databaseViewModel = new DatabaseViewModel(model);
    this.baristaHandler = new BaristaHandler();
    this.baristaViewModel = new BaristaViewModel(model, baristaHandler);
    this.orderDetailViewModel = new OrderDetailViewModel(model, baristaHandler);
    this.checkoutViewModel = new CheckoutViewModel(model);
    this.descriptionViewModel = new DescriptionViewModel(model);
    this.customerViewModel = new CustomerViewModel(model, descriptionViewModel);
    this.cashierHandler = new CashierHandler();
    this.cashierViewModel = new CashierViewModel(model, cashierHandler);
    this.editCommentViewModel = new EditCommentViewModel(model, cashierHandler);
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

  public CashierViewModel getCashierViewModel()
  {
    return cashierViewModel;
  }

  public EditCommentViewModel getEditCommentViewModel()
  {
    return editCommentViewModel;
  }

  public CashierHandler getCashierHandler()
  {
    return cashierHandler;
  }

  public BaristaHandler getBaristaHandler()
  {
    return baristaHandler;
  }

  public OrderDetailViewModel getOrderDetailViewModel()
  {
    return orderDetailViewModel;
  }

}
