import mediator.RemoteServer;
import utility.Item;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class RMITest
{
  public static void main(String[] args)
  {
    try
    {
      RemoteServer server = new RemoteServer();
      server.addItemToProductList(new Item("drink","coffee", 15, "mmmm"));
    }
    catch (RemoteException | SQLException | MalformedURLException e)
    {
      e.printStackTrace();
    }
  }
}
