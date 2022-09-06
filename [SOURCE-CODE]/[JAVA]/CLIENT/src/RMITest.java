import mediator.RemoteClient;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMITest {
    public static void main(String[] args) {
        try {
            RemoteClient client = new RemoteClient();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
