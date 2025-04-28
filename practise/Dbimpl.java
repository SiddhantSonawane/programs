
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class Dbimpl extends UnicastRemoteObject implements Dbinterface {

    public Dbimpl() throws RemoteException {
        super();
    }

    public int add(int a, int b) throws RemoteException{
        return a+b;
    }

    public String comparison (String a, String b) throws RemoteException{
        return (a.compareTo(b) > 0) ? a : b;
    }
}
