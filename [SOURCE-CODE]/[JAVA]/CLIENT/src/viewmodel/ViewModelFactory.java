package viewmodel;

import model.Model;

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
  private EditCommentCashierViewModel editCommentCashierViewModel;
  private CashierHandler cashierHandler;
  private ExtraViewModel extraViewModel;
  private DisplayViewModel displayViewModel;
  private ManageAccessKeysViewModel manageAccessKeysViewModel;
  private AddAccessKeyViewModel addAccessKeyViewModel;
  private EditCommentCustomerViewModel editCommentCustomerViewModel;
  private CustomerHandler customerHandler;
  private PaymentViewModel paymentViewModel;
  private ExtraDatabaseViewModel extraDatabaseViewModel;
  private AddExtraViewModel addExtraViewModel;

  public ViewModelFactory(Model model)
  {
    this.startViewModel = new StartViewModel(model);
    this.loginViewModel = new LoginViewModel(model);
    this.addItemViewModel = new AddItemViewModel(model);
    this.databaseViewModel = new DatabaseViewModel(model);
    this.customerHandler = new CustomerHandler();
    this.baristaHandler = new BaristaHandler();
    this.baristaViewModel = new BaristaViewModel(model, baristaHandler);
    this.orderDetailViewModel = new OrderDetailViewModel(model, baristaHandler);
    this.checkoutViewModel = new CheckoutViewModel(model);
    this.descriptionViewModel = new DescriptionViewModel(model, customerHandler);
    this.cashierHandler = new CashierHandler();
    this.cashierViewModel = new CashierViewModel(model, cashierHandler);
    this.editCommentCashierViewModel = new EditCommentCashierViewModel(model, cashierHandler);
    this.extraViewModel = new ExtraViewModel(model, customerHandler);
    this.customerViewModel = new CustomerViewModel(model,customerHandler);
    this.displayViewModel = new DisplayViewModel(model);
    this.manageAccessKeysViewModel = new ManageAccessKeysViewModel(model);
    this.addAccessKeyViewModel = new AddAccessKeyViewModel(model);
    this.editCommentCustomerViewModel = new EditCommentCustomerViewModel(model);
    this.paymentViewModel = new PaymentViewModel(model);
    this.extraDatabaseViewModel = new ExtraDatabaseViewModel(model);
    this.addExtraViewModel = new AddExtraViewModel(model);
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

  public AddAccessKeyViewModel getAddAccessKeyViewModel()
  {
    return addAccessKeyViewModel;
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

  public EditCommentCashierViewModel getEditCommentCashierViewModel()
  {
    return editCommentCashierViewModel;
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

  public ExtraViewModel getExtraViewModel()
  {
    return extraViewModel;
  }

  public DisplayViewModel getDisplayViewModel()
  {
    return displayViewModel;
  }

  public ManageAccessKeysViewModel getManageAccessKeysViewModel()
  {
    return manageAccessKeysViewModel;
  }

  public EditCommentCustomerViewModel getEditCommentCustomerViewModel()
  {
    return editCommentCustomerViewModel;
  }
  public PaymentViewModel getPaymentViewModel()
  {
    return paymentViewModel;
  public ExtraDatabaseViewModel getExtraDatabaseViewModel()
  {
    return extraDatabaseViewModel;
  }

  public AddExtraViewModel getAddExtraViewModel()
  {
    return addExtraViewModel;
  }
}
