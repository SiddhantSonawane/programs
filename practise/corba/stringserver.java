import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;

public class stringserver{
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            StringOperationImpl strop = new StringOperationImpl();

            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(strop);

            org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
            NamingContextExt ncref = NamingContextExtHelper.narrow(objref);


            String name = "StringOperation";
            NameComponent path[] = ncref.to_name(name);
            ncref.rebind(path, ref);

            System.out.println("Server ready and waiting...");
            orb.run();
        } catch (Exception e) {
            System.out.println("exception in server " + e);
        }
    }
}