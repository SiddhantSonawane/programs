import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DistributedServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            DistributedImplementation obj = new DistributedImplementation();
            registry.rebind("DistributedServer", obj);
            System.out.println("Server is ready...");
        } catch (Exception e) {
            System.out.println("Server Exception: " + e);
        }
    }
}
