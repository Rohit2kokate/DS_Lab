
import mpi.MPI;

public class mpj {
    public static void main(String args[]) {
        // Initialize MPI execution environment
        MPI.Init(args);

        // Get the id of the process
        int rank = MPI.COMM_WORLD.Rank();

        // Total number of processes
        int size = MPI.COMM_WORLD.Size();

        int root = 0; // Root process id
        int sendbuf[] = null; // Array to be scattered

        if (rank == root) {
            // Initialize the data to be scattered at the root process
            sendbuf = new int[size];
            sendbuf[0] = 10;
            sendbuf[1] = 20;
            sendbuf[2] = 30;
            sendbuf[3] = 40;

            System.out.print("Processor " + rank + " has data: ");
            for (int i = 0; i < size; i++) {
                System.out.print(sendbuf[i] + " ");
            }
            System.out.println();
        }

        // Buffer to receive scattered data
        int recvbuf[] = new int[1];

        // Scatter operation: distribute data from root to all processes
        MPI.COMM_WORLD.Scatter(sendbuf, 0, 1, MPI.INT, recvbuf, 0, 1, MPI.INT, root);

        // Each process displays its received data
        System.out.println("Processor " + rank + " received data: " + recvbuf[0]);

        // Each process doubles its received data
        System.out.println("Processor " + rank + " is doubling the data.");
        recvbuf[0] *= 2;

        // Gather operation: collect the modified data at the root process
        MPI.COMM_WORLD.Gather(recvbuf, 0, 1, MPI.INT, sendbuf, 0, 1, MPI.INT, root);

        // Root process displays the gathered data
        if (rank == root) {
            System.out.println("Processor " + rank + " has gathered data: ");
            for (int i = 0; i < size; i++) {
                System.out.print(sendbuf[i] + " ");
            }
            System.out.println();
        }

        // Terminate MPI execution environment
        MPI.Finalize();
    }
}

