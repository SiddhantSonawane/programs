import mpi.*;

public class sum{
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int unitSize = 5;

        int[] sendBuffer = new int[unitSize * size];
        int[] recvBuffer = new int[unitSize];

        if(rank == 0){
            for(int i = 0; i < unitSize * size; i++){
                sendBuffer[i] = i+1;
            }
        }

        MPI.COMM_WORLD.Scatter(sendBuffer, 0, unitSize, MPI.INT, recvBuffer, 0, unitSize, MPI.INT, 0);

        int localSum = 0;
        for(int i = 0; i < unitSize; i++){
            localSum += recvBuffer[i];
        }
        System.out.println("Process " + rank + " local sum: " + localSum);
        int[] finalSum = new int[1]; 
        MPI.COMM_WORLD.Reduce(new int[]{localSum}, 0, finalSum, 0, 1, MPI.INT, MPI.SUM, 0);

        if(rank == 0) {
            System.out.println("finalSum is " + finalSum[0]);
        }
    }
}