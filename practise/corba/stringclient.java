import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import stringmodule.StringOperation;
import stringmodule.StringOperationHelper;
import java.util.Scanner;

public class stringclient{
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
            NamingContextExt ncref = NamingContextExtHelper.narrow(objref);

            StringOperation strOp = StringOperationHelper.narrow(ncref.resolve_str("StringOperation"));

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter a string:");
            String input = sc.nextLine();

            String result = "";

            result = strOp.revStr(input);
            System.out.println("Result: " + result);
        } catch(Exception e) {
            System.out.println("exception " + e);
        }
    }
}