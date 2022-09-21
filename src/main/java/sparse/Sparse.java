package sparse;

import java.util.Scanner;

public class Sparse {

    // Sparse matrix having size 4*5  
    private static int array[][]
            = {
                {0, 0, 6, 0, 9},
                {0, 0, 4, 6, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 2, 0, 0}
            };

    private static int compactMatrix[][];

    public Sparse() {

    }

    private static void read() {
        int i, j;

        System.out.println("Enter total rows and columns: ");
        Scanner s = new Scanner(System.in);
        int row = s.nextInt();
        int column = s.nextInt();

        array = new int[row][column];

        System.out.println("Enter matrix:");
        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                array[i][j] = s.nextInt();
                System.out.print(" ");
            }
        }
    }

    public static boolean isSparse() {
        int i, j, zero = 0, count = 0;
        for (i = 0; i < array.length; i++) {
            for (j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    zero++;
                } else {
                    count++;
                }
            }
        }
        if (zero > count) {
            return true;
        } else {
            return false;
        }
    }

    public static void print() {
        int i, j;
        for (i = 0; i < array.length; i++) {
            for (j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void compact() {
        // Count non zero entries of the sparse matrix
        int nonZeroCount = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    nonZeroCount++;
                }
            }
        }

        // Create compact matrix
        compactMatrix = new int[3][nonZeroCount];
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    compactMatrix[0][k] = i;
                    compactMatrix[1][k] = j;
                    compactMatrix[2][k] = array[i][j];
                    k++;
                }
            }
        }

    }

    public static void printCompactMatrix() {
        for (int i = 0; i < compactMatrix.length; i++) {
            for (int j = 0; j < compactMatrix[i].length; j++) {
                System.out.print(compactMatrix[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    public static void main(String args[]) {

        // read();
        if (isSparse()) {
            System.out.println("the matrix is sparse");
        } else {
            System.out.println("the matrix is not sparse");
        }
        System.out.println("The sparse matrix is ...");
        print();

        compact();

        System.out.println("The compact matrix is ...");
        printCompactMatrix();
    }
}
