import model.ModelManager;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import model.*;
import utility.Item;

public class RMITest
{
  public static void main(String[] args)
  {
    try
    {
      Model model = new ModelManager();
      model.addItemToOrder(new Item(1, "Coffee", "coffee", 10.00));
      model.submitOrder();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
