import java.util.Scanner;

class MatrixMultiply extends Thread {
    private int row;
    private int col;
    private int[][] A, B, C;
    private int size;

    public MatrixMultiply(int row, int col, int[][] A, int[][] B, int[][] C, int size) {
        this.row = row;
        this.col = col;
        this.A = A;
        this.B = B;
        this.C = C;
        this.size = size;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int k = 0; k < size; k++) {
            sum += A[row][k] * B[k][col];
        }
        C[row][col] = sum;

        try {
            Thread.sleep(100); 
        } catch (Exception e) {}
    }
}

public class javathreads_matrix {
    public static void main(String[] args) {
        Scanner matrixInput = new Scanner(System.in);
        int size;

        System.out.print("Enter matrix size (n x n): ");
        size = matrixInput.nextInt();

        int[][] A = new int[size][size];
        int[][] B = new int[size][size];
        int[][] C = new int[size][size];

    // Input Matrix A 
        System.out.println("\nEnter elements for Matrix A:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("Enter element A[%d][%d]: ", i+1, j+1);
                A[i][j] = matrixInput.nextInt();
            }
        }

    // Input Matrix B 
        System.out.println("\nEnter elements for Matrix B:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("Enter element B[%d][%d]: ", i+1, j+1);
                B[i][j] = matrixInput.nextInt();
            }
        }

        MatrixMultiply[][] threads = new MatrixMultiply[size][size];

    // Create and start threads
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                threads[i][j] = new MatrixMultiply(i, j, A, B, C, size);
                threads[i][j].start();
            }
        }

    // Join threads
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    threads[i][j].join();
                } catch (InterruptedException e) {}
            }
        }

    // Print result Matrix
        System.out.println("\nResult Matrix C :");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%6d", C[i][j]); 
            }
            System.out.println();
        }

        matrixInput.close();
    }
}