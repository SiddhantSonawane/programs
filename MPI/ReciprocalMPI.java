import mpi.*;

public class ReciprocalMPI {
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        double[] sendBuffer = new double[size];
        double[] recvBuffer = new double[1]; // each process gets 1 element

        if (rank == 0) {
            // Initialize the array
            System.out.println("Original array at root:");
            for (int i = 0; i < size; i++) {
                sendBuffer[i] = i + 1; // avoid zero to prevent divide by zero
                System.out.print(sendBuffer[i] + " ");
            }
            System.out.println();
        }

        // Scatter one element to each process
        MPI.COMM_WORLD.Scatter(sendBuffer, 0, 1, MPI.DOUBLE,
                               recvBuffer, 0, 1, MPI.DOUBLE, 0);

        // Each process calculates reciprocal
        recvBuffer[0] = 1.0 / recvBuffer[0];

        // Gather reciprocals back to root
        double[] resultBuffer = new double[size];
        MPI.COMM_WORLD.Gather(recvBuffer, 0, 1, MPI.DOUBLE,
                              resultBuffer, 0, 1, MPI.DOUBLE, 0);

        if (rank == 0) {
            System.out.println("Reciprocal array at root:");
            for (int i = 0; i < size; i++) {
                System.out.printf("%.4f ", resultBuffer[i]);
            }
            System.out.println();
        }

        MPI.Finalize();
    }
}
//Steps to run this program 
/*
 * FOR WINDOWS SYSTEMS:

 * 1) Unzip the "mpjexpress" into the "C drive" of the machine such that "C:\mpjexpress"
 * 2) Steps to run the code 
 *      2.1) Set Environment Variables (Temporary, in your Command Prompt of the system not in VScode)
 *          set MPJ_HOME=C:\mpjexpress
            set PATH=%PATH%;%MPJ_HOME%\bin
        2.2) Compile your code with MPJ library
            javac -cp .;%MPJ_HOME%\lib\mpj.jar SimpleDistributedSum.java
        2.3) Run using mpjrun
            mpjrun.bat -np 4 SimpleDistributedSum
            Here 4 means 4 process 
 */

/*
  * FOR LINUX SYSTEMS:

  * export MPJ_HOME=/home/ubuntu/Downloads/mpj-v0_44
  * export PATH=$MPJ_HOME/bin:$PATH
  * javac -cp $MPJ_HOME/lib/mpj.jar ArrSum.java
  * $MPJ_HOME/bin/mpjrun.sh -np 4 ArrSum
*/