package mediator;

import utility.*;
import utility.observer.event.ObserverEvent;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.PropertyChangeHandler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class RemoteServerClient implements RemoteCafeServer, RemoteListener<String, String>, UnnamedPropertyChangeSubject

{
        private RemoteCafeServer server;
        private PropertyChangeSupport property;
        private PropertyChangeHandler<String, String> propertyChange;

        public RemoteServerClient()
                throws MalformedURLException, NotBoundException, RemoteException
        {
            server = (RemoteCafeServer) Naming.lookup("rmi://localhost:1888/CafeDAO");
            UnicastRemoteObject.exportObject(this, 0);
            server.addListener(this);
            property= new PropertyChangeSupport(this);
            startRegistry();
            startServer();
            propertyChange = new PropertyChangeHandler<>(this);
        }

    private void startRegistry() throws RemoteException
    {
        try
        {
            Registry reg = LocateRegistry.createRegistry(1099);
            System.out.println("Registry started...");
        }
        catch (java.rmi.server.ExportException e)
        {
            System.out.println("Registry already started? " + e.getMessage());
        }
    }

    private void startServer() throws RemoteException, MalformedURLException
    {
        UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind("Cafe", this);
        System.out.println("Server started...");
    }

        public ItemList getAllItems() throws RemoteException, SQLException
        {
            return server.getAllItems();
        }

        public ItemList getItemsByType(String type)
                throws RemoteException, SQLException
        {
            return server.getItemsByType(type);
        }

        public ArrayList<AccessKey> getAllAccessKey()
                throws RemoteException, SQLException
        {
            return server.getAllAccessKey();
        }

        public int receiveOrder(Order order) throws RemoteException
        {
            return server.receiveOrder(order);
        }

        public void completeOrder(Order order) throws RemoteException
        {
            server.completeOrder(order);
        }

        public void cancelOrder(Order order) throws RemoteException
        {
            server.cancelOrder(order);
        }

        public void editCommentInOrder(Order order, String comment)
                throws RemoteException
        {
            server.editCommentInOrder(order, comment);
        }

        public void receiveUnpaidOrder(Order order) throws RemoteException
        {
            server.receiveUnpaidOrder(order);
        }

        public void acceptPayment(Order order) throws RemoteException
        {
            server.acceptPayment(order);
        }

        public void addItemToProductList(Item item) throws RemoteException
        {
            server.addItemToProductList(item);
        }

        public void addAccessKey(AccessKey accessKey) throws RemoteException
        {
            server.addAccessKey(accessKey);
        }

        public ArrayList<Order> getAllPendingOrders() throws RemoteException
        {
            return server.getAllPendingOrders();
        }

        public ArrayList<Order> getAllUnpaidOrders() throws RemoteException
        {
            return server.getAllUnpaidOrders();
        }

        public ArrayList<Order> getAllCompletedOrders() throws RemoteException
        {
            return server.getAllCompletedOrders();
        }

    @Override
    public boolean addListener(GeneralListener<String, String> listener, String... propertyNames) throws RemoteException {
        return false;
    }

    @Override
    public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames) throws RemoteException {
        return false;
    }

    public void removeItemFromProductList(Item item)
                throws RemoteException, SQLException
        {
            server.removeItemFromProductList(item);
        }

        public ArrayList<Extra> getAllExtrasByType(String type) throws RemoteException, SQLException {
            return server.getAllExtrasByType(type);
        }

        @Override public void addListener(PropertyChangeListener listener)
        {
            property.addPropertyChangeListener(listener);
        }

        @Override public void removeListener(PropertyChangeListener listener)
        {
            property.removePropertyChangeListener(listener);
        }

        @Override public void propertyChange(ObserverEvent<String, String> event)
                throws RemoteException
        {
            propertyChange.firePropertyChange("change", event.getValue1(), event.getValue2());
        }

        public void removeAccessKey(AccessKey accessKey) throws RemoteException, SQLException {
            server.removeAccessKey(accessKey);
        }

        public String getUserType(String pwd) throws RemoteException, SQLException
        {
            String ak = server.getUserType(pwd);
            return ak;
        }

        public ArrayList<Extra> getAllExtras() throws RemoteException, SQLException {
            return server.getAllExtras();
        }

        public void addExtraToExtraList(Extra extra) throws RemoteException, SQLException {
            server.addExtraToExtraList(extra);
        }

        public void removeExtraFromExtraList(Extra extra)
                throws RemoteException, SQLException {
            server.removeExtraFromExtraList(extra);
        }

    }

