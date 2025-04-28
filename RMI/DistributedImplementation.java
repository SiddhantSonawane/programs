import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class DistributedImplementation extends UnicastRemoteObject implements DistributedInterface {
    
    public DistributedImplementation() throws RemoteException {
        super();
    }

    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }

    public int multiply(int a, int b) throws RemoteException {
        return a * b;
    }

    public double divide(int a, int b) throws RemoteException {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return (double) a / b;
    }

    public double powerOfTwo(int n) throws RemoteException {
        return Math.pow(2, n);
    }

    public double celsiusToFahrenheit(double celsius) throws RemoteException {
        return (celsius * 9/5) + 32;
    }

    public double milesToKilometers(double miles) throws RemoteException {
        return miles * 1.60934;
    }

    public String echo(String name) throws RemoteException {
        return "Hello " + name;
    }

    public String compareStrings(String s1, String s2) throws RemoteException {
        return (s1.compareTo(s2) > 0) ? s1 : s2;
    }

    public int countVowels(String word) throws RemoteException {
        int count = 0;
        word = word.toLowerCase();
        for (char c : word.toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count;
    }

    public long factorial(int n) throws RemoteException {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
