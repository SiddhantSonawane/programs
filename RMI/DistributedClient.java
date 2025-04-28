import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class DistributedClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            DistributedInterface stub = (DistributedInterface) registry.lookup("DistributedServer");

            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Addition");
                System.out.println("2. Subtraction");
                System.out.println("3. Multiplication");
                System.out.println("4. Division");
                System.out.println("5. Power of 2");
                System.out.println("6. Celsius to Fahrenheit");
                System.out.println("7. Miles to Kilometers");
                System.out.println("8. Echo Name");
                System.out.println("9. Compare Two Strings");
                System.out.println("10. Count Vowels");
                System.out.println("11. Factorial");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter two numbers: ");
                        int a = sc.nextInt();
                        int b = sc.nextInt();
                        System.out.println("Addition = " + stub.add(a, b));
                        break;
                    case 2:
                        System.out.print("Enter two numbers: ");
                        a = sc.nextInt();
                        b = sc.nextInt();
                        System.out.println("Subtraction = " + stub.subtract(a, b));
                        break;
                    case 3:
                        System.out.print("Enter two numbers: ");
                        a = sc.nextInt();
                        b = sc.nextInt();
                        System.out.println("Multiplication = " + stub.multiply(a, b));
                        break;
                    case 4:
                        System.out.print("Enter two numbers: ");
                        a = sc.nextInt();
                        b = sc.nextInt();
                        System.out.println("Division = " + stub.divide(a, b));
                        break;
                    case 5:
                        System.out.print("Enter number: ");
                        int n = sc.nextInt();
                        System.out.println("2^" + n + " = " + stub.powerOfTwo(n));
                        break;
                    case 6:
                        System.out.print("Enter Celsius: ");
                        double celsius = sc.nextDouble();
                        System.out.println("Fahrenheit = " + stub.celsiusToFahrenheit(celsius));
                        break;
                    case 7:
                        System.out.print("Enter Miles: ");
                        double miles = sc.nextDouble();
                        System.out.println("Kilometers = " + stub.milesToKilometers(miles));
                        break;
                    case 8:
                        sc.nextLine(); // consume newline
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.println(stub.echo(name));
                        break;
                    case 9:
                        sc.nextLine(); // consume newline
                        System.out.print("Enter first string: ");
                        String s1 = sc.nextLine();
                        System.out.print("Enter second string: ");
                        String s2 = sc.nextLine();
                        System.out.println("Lexicographically larger: " + stub.compareStrings(s1, s2));
                        break;
                    case 10:
                        sc.nextLine(); // consume newline
                        System.out.print("Enter word: ");
                        String word = sc.nextLine();
                        System.out.println("Vowel Count = " + stub.countVowels(word));
                        break;
                    case 11:
                        System.out.print("Enter number: ");
                        n = sc.nextInt();
                        System.out.println("Factorial = " + stub.factorial(n));
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } while (choice != 0);

            sc.close();

        } catch (Exception e) {
            System.out.println("Client Exception: " + e);
        }
    }
}
