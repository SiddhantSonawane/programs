
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Dbinterface extends Remote{
    int add( int a, int b) throws RemoteException;
    String comparison( String a, String b) throws RemoteException;
}
