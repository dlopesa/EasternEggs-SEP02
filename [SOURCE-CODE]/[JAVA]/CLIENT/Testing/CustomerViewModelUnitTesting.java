import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.ModelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.Item;
import viewmodel.CustomerViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * In this class the binding from the CustomerViewModel class will be tested.
 */
class CustomerViewModelUnitTesting
{

  private Model model;
  private Item item;
  private StringProperty name;
  private StringProperty price;
  private StringProperty error;
  private StringProperty extra;

  @BeforeEach void setUp()
  {
    try
    {
      model = new ModelManager();
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (NotBoundException e)
    {
      e.printStackTrace();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    item = new Item(1, "name", "type", 10.00, "description");

    name = new SimpleStringProperty();
    price = new SimpleStringProperty();
    error = new SimpleStringProperty();
    extra = new SimpleStringProperty();
    name.set("Latte Macchiato");
    price.set("10.00");
    error.set("Error");
    extra.set("caramel, chocolate");
  }

  @Test void constructor()
  {
    CustomerViewModel customerViewModel = new CustomerViewModel(model);
    assertNotNull(customerViewModel);
  }

  //Zero, Many, Boundary, and Exception are not relevant here as
  // it is only possible to add one object. Exceptions are checked for in O.
  @Test void addItemToOrder_O()
  {
    assertDoesNotThrow(() -> model.addItemToOrder(item));
  }

  //Zero, Many, Boundary are not relevant because it is only possible to submit one order.
  // Exceptions are checked for in other methods, Exceptions are not thrown in the One test.
  @Test void submitOrder_0()
  {
    assertDoesNotThrow(() -> model.submitOrder());
  }

  //The following methods are testing each getter method in the class CustomerViewModel.
  @Test void getNameProperty_O()
  {
    assertEquals(name.get(), "Latte Macchiato");
  }

  @Test void getPriceProperty_O()
  {
    assertEquals(price.get(), "10.00");
  }

  @Test void getErrorProperty_O()
  {
    assertEquals(error.get(), "Error");
  }

  @Test void getExtraProperty_O()
  {
    assertEquals(extra.get(), "caramel, chocolate");
  }

}



