import mpi.*;
import java.util.Random;

public class AverageMPI {
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();  // Process ID
        int size = MPI.COMM_WORLD.Size();  // Total processes

        int unitSize = 5; // Numbers per process
        int[] sendBuffer = new int[unitSize * size];
        int[] recvBuffer = new int[unitSize];

        if (rank == 0) {
            // Root process generates random numbers
            Random rand = new Random();
            System.out.println("Generated random numbers:");
            for (int i = 0; i < sendBuffer.length; i++) {
                sendBuffer[i] = rand.nextInt(100); // Random numbers between 0 and 99
                System.out.print(sendBuffer[i] + " ");
            }
            System.out.println();
        }

        // Scatter numbers to all processes
        MPI.COMM_WORLD.Scatter(sendBuffer, 0, unitSize, MPI.INT,
                               recvBuffer, 0, unitSize, MPI.INT, 0);

        // Each process computes its local average
        int localSum = 0;
        for (int i = 0; i < unitSize; i++) {
            localSum += recvBuffer[i];
        }
        double localAverage = (double) localSum / unitSize;
        System.out.println("Process " + rank + " local average: " + localAverage);

        // Each process sends its local average to the root
        double[] gatheredAverages = new double[size];
        MPI.COMM_WORLD.Gather(new double[]{localAverage}, 0, 1, MPI.DOUBLE,
                              gatheredAverages, 0, 1, MPI.DOUBLE, 0);

        if (rank == 0) {
            // Root process computes final average
            double finalSum = 0;
            for (int i = 0; i < size; i++) {
                finalSum += gatheredAverages[i];
            }
            double finalAverage = finalSum / size;
            System.out.println("Final average: " + finalAverage);
        }

        MPI.Finalize();
    }
}
