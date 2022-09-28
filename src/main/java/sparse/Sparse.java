package sparse;

import java.util.Scanner;

public class Sparse {

    // private static int packedMatrix[][];
    public Sparse() {

    }

    private static int[][] read() {
        int i, j;

        System.out.println("Enter total rows and columns: ");
        Scanner s = new Scanner(System.in);
        int row = s.nextInt();
        int column = s.nextInt();

        int[][] array = new int[row][column];

        System.out.println("Enter matrix:");
        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                array[i][j] = s.nextInt();
                System.out.print(" ");
            }
        }

        return array;
    }

    public static boolean isSparse(int[][] array) {
        int i, j, zero = 0, count = 0;
        // array.length returns the number of rows
        for (i = 0; i < array.length; i++) {
            // < array[i].length returns the number of colums of the ith row
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

    public static void print(int array[][]) {
        int i, j;
        for (i = 0; i < array.length; i++) {
            for (j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static int[][] pack(int[][] array) {
        int[][] packedMatrix;

        // Count non zero entries of the sparse matrix
        int nonZeroCount = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    nonZeroCount++;
                }
            }
        }

        // Create pack matrix
        packedMatrix = new int[3][nonZeroCount];
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    packedMatrix[0][k] = i;
                    packedMatrix[1][k] = j;
                    packedMatrix[2][k] = array[i][j];
                    k++;
                }
            }
        }

        return packedMatrix;

    }

    public static int[][] unpack(int[][] packedMatrix) {
        // Find number of rows and colums of the unpacked matrix

        int rowsNumber = packedMatrix[0][0];
        int columsNumber = packedMatrix[1][0];

        for (int i = 0; i < packedMatrix[0].length; i++) {
            if (packedMatrix[0][i] > rowsNumber) {
                rowsNumber = packedMatrix[0][i];
            }
            if (packedMatrix[1][i] > columsNumber) {
                columsNumber = packedMatrix[1][i];
            }

        }

        int[][] unpackedMatrix = new int[rowsNumber+1][columsNumber+1];

        for (int i = 0; i < packedMatrix[0].length; i++) {
            int row = packedMatrix[0][i];
            int column = packedMatrix[1][i];
            unpackedMatrix[row][column] = packedMatrix[2][i];
        }

        return unpackedMatrix;

    }

    public static void main(String args[]) {
        // Sparse matrix having size 4*5  

        int array[][]
                = {
                    {0, 0, 6, 0, 9},
                    {0, 0, 4, 6, 0},
                    {0, 0, 0, 0, 0},
                    {0, 1, 2, 0, 0}
                };

        // read();
        if (isSparse(array)) {
            System.out.println("the matrix is sparse");
        } else {
            System.out.println("the matrix is not sparse");
        }

        System.out.println(
                "The sparse matrix is ...");
        print(array);

        int[][] packedtMatrix = pack(array);

        System.out.println(
                "The compact matrix is ...");
        print(packedtMatrix);

        int[][] unpackedtMatrix = unpack(packedtMatrix);

        System.out.println(
                "The unpacked matrix again is ...");
        print(unpackedtMatrix);

    }
}
