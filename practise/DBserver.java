
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DBserver {
    public static void main (String [] args) {
        try{
            Registry registry = LocateRegistry.getRegistry();
            Dbimpl obj = new Dbimpl();
            registry.rebind("server", obj);
            System.out.println("Server is ready!");
        } catch(Exception e){
            System.out.println("Server exception " + e);
        }
    }
}
