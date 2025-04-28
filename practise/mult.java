import mpi.*;

public class mult {
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank(); // Get current process id
        int size = MPI.COMM_WORLD.Size(); // Get total number of processes

        int unitSize = 5; // Number of elements each process will handle

        int[] sendBuffer = new int[unitSize * size];
        int[] recvBuffer = new int[unitSize];

        if (rank == 0) {
            // Root process fills the array
            for (int i = 0; i < unitSize * (size / 2); i++) {
                sendBuffer[i] = i + 1; // Example: [1,2,3,...]
            }
            for (int i = 10; i < unitSize * (size / 2) * 2; i++) {
                sendBuffer[i] = i - 10 + 1; // Example: [1,2,3,...]
            }
        }

        // Scatter data from root to all processes
        MPI.COMM_WORLD.Scatter(sendBuffer, 0, unitSize, MPI.INT,
                recvBuffer, 0, unitSize, MPI.INT, 0);

        // Each process computes local multiplication
        int localProduct = 1;
        for (int i = 0; i < unitSize; i++) {
            localProduct *= recvBuffer[i];
        }

        System.out.println("Process " + rank + " local product: " + localProduct);

        // Root will gather the final product
        int[] finalProduct = new int[1];
        MPI.COMM_WORLD.Reduce(new int[] { localProduct }, 0, finalProduct, 0, 1, MPI.INT, MPI.PROD, 0);

        if (rank == 0) {
            System.out.println("Final product of all numbers: " + finalProduct[0]);
        }

        MPI.Finalize();
    }
}
