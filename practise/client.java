
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Dbinterface stub = (Dbinterface) registry.lookup("server");

            Scanner sc = new Scanner(System.in);
            int ch;

            do {
                System.out.println("Menu");
                System.out.println("Addition");
                System.out.println("comparison");
                System.out.println("exit");
                System.out.println("Enter choice");
                ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        int a, b;
                        System.out.println("Enter first number: ");
                        a = sc.nextInt();
                        System.out.println("Enter second number: ");
                        b = sc.nextInt();
                        System.out.println("Addition is " + stub.add(a, b));
                        break;
                    case 2:
                        String s1, s2;
                        sc.nextLine();
                        System.out.println("Enter first string: ");
                        s1 = sc.nextLine();
                        System.out.println("Enter second string: ");
                        s2 = sc.nextLine();
                        System.out.println("Bigger is " + stub.comparison(s1, s2));
                        break;
                    case 3:
                        System.out.println("exit");
                        break;

                    default:
                        System.out.println("Invalid choice");
                }
            } while (ch != 3);
            sc.close();
        } catch (Exception e) {
            System.out.println("client exception " + e);// TODO: handle exception
        }
    }
}
