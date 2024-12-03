import java.util.Scanner;

public class Berkeley {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] times = new int[n];
        System.out.println("Enter the clock times of processes:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            times[i] = sc.nextInt();
        }

        int sum = 0;
        for (int time : times) {
            sum += time;
        }

        int average = sum / n;
        System.out.println("Average time: " + average);

        System.out.println("Adjusted times:");
        for (int i = 0; i < n; i++) {
            int adjustment = average - times[i];
            System.out.println("Process " + (i + 1) + ": " + (times[i] + adjustment));
        }

        sc.close();
    }
}

/*PS C:\Users\HP\Desktop\chitu\Assignment 7> javac BerkeleyAlgorithm.java
PS C:\Users\HP\Desktop\chitu\Assignment 7> java BerkeleyAlgorithm
Enter the number of processes: 4
Enter the clock times of processes:
Process 1: 100
Process 2: 200
Process 3: 150
Process 4: 180
Average time: 157
Adjusted times:
Process 1: 157
Process 2: 157
Process 3: 157
Process 4: 157
PS C:\Users\HP\Desktop\chitu\Assignment 7>  */