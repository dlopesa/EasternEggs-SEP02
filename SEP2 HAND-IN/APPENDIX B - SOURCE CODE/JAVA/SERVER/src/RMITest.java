import mediator.RemoteServer;

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
    }
    catch (RemoteException | SQLException | MalformedURLException e)
    {
      e.printStackTrace();
    }
  }
}
