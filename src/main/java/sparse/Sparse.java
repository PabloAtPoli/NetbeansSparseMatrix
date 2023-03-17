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
        int i, j, countZero = 0, countNoZero = 0;
        // array.length returns the number of rows
        for (i = 0; i < array.length; i++) {
            // array[i].length returns the number of colums of the ith row
            for (j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    countZero++;
                } else {
                    countNoZero++;
                }
            }
        }
        if (countZero > countNoZero) {
            return true;
        } else {
            return false;
        }
    }

    public static void print(int array[][]) {
        int i, j;
        for (i = 0; i < array.length; i++) {
            for (j = 0; j < array[i].length; j++) {
                System.out.printf("%4d", array[i][j]);
            }
            System.out.println("");
        }
    }

    public static int[][] pack(int[][] array) {
        int[][] packedMatrix;

        // Count non Zero entries of the sparse matrix
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
        // Find the greatest row and column in the packed matrix
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

        //It creates the sparse matrix
        int[][] sparseMatrix = new int[rowsNumber + 1][columsNumber + 1];

        // It assigns non zero entries from packed matrix to the sparse matrix
        for (int i = 0; i < packedMatrix[0].length; i++) {
            int row = packedMatrix[0][i];
            int column = packedMatrix[1][i];
            sparseMatrix[row][column] = packedMatrix[2][i];
        }

        return sparseMatrix;

    }

    public static int[][] addPackedMatrixes(int[][] packedMatrix1, int[][] packedMatrix2) {
        // Count non zero equal entries in both matrixes
        int countEqualEntries = 0;
        for (int i = 0; i < packedMatrix1[0].length; i++) {
            for (int j = 0; j < packedMatrix2[0].length; j++) {
                if ((packedMatrix1[0][i] == packedMatrix2[0][j]) && 
                        (packedMatrix1[1][i] == packedMatrix2[1][j])) {
                    countEqualEntries++;
                }
            }
        }

        // It calculates the number of colums of the addition packed matrix
        int lengthAddition = packedMatrix1[0].length + packedMatrix2[0].length - countEqualEntries;

        // It creates the addition matrix
        int[][] additionMatrix = new int[3][lengthAddition];

        // This unidimensional array records the entries in  packedMatrix2
        // that has not been added to additionMatrix
        boolean[] isEntryAdded = new boolean[packedMatrix2[0].length];

        // variable k counts the number of entries in addition matrix
        int k = 0;
        for (int i = 0; i < packedMatrix1[0].length; i++) {
            int sum = packedMatrix1[2][i];
            for (int j = 0; j < packedMatrix2[0].length; j++) {
                if ((packedMatrix1[0][i] == packedMatrix2[0][j]) && 
                        (packedMatrix1[1][i] == packedMatrix2[1][j])) {
                    sum += packedMatrix2[2][j];
                    isEntryAdded[j] = true;
                }
            }
            // It assigns the correspondies entries of packed1 and packed 2 matrixex to addition Matrix

            additionMatrix[0][i] = packedMatrix1[0][i];
            additionMatrix[1][i] = packedMatrix1[1][i];
            additionMatrix[2][i] = sum;
            k++;
        }

        // Add the entries in packedMatrix2 that has not been added to additionMatrix
        for (int i = 0; i < isEntryAdded.length; i++) {
            if (!isEntryAdded[i]) {
                    additionMatrix[0][k] = packedMatrix2[0][i];
                    additionMatrix[1][k] = packedMatrix2[1][i];
                    additionMatrix[2][k] = packedMatrix2[2][i];
                    k++;
            }
        }
        return additionMatrix;
    }


    public static void main(String args[]) {
        // Sparse matrix having size 4*5  

        int[][] sparse1
                = {
                    {0, 0, 6, 0, 9},
                    {0, 0, 4, 6, 0},
                    {0, 0, 0, 0, 0},
                    {0, 1, 2, 0, 0}
                };

//        int[][] sparse1 = read();
        if (isSparse(sparse1)) {
            System.out.println("the matrix 1 is sparse");
        } else {
            System.out.println("the matrix 1 is not sparse");
        }

        System.out.println(
                "The sparse matrix 1 is ...");
        print(sparse1);

        int[][] packed1 = pack(sparse1);

        System.out.println(
                "The compact matrix 1 is ...");
        print(packed1);

        int[][] unpacked1 = unpack(packed1);

//        System.out.println(
//                "The sparse matrix 1 again is ...");
//        print(unpacked1);

        int[][] sparse2
                = {
                    {10, 0, 6, 0, 9},
                    {0, 0, 4, 6, 0},
                    {0, 0, 0, 0, 0},
                    {0, 1, 2, 0, 10}
                };

        // read();
        if (isSparse(sparse2)) {
            System.out.println("the matrix 2 is sparse");
        } else {
            System.out.println("the matrix 2 is not sparse");
        }

        System.out.println(
                "The sparse matrix 2 is ...");
        print(sparse2);

        int[][] packed2 = pack(sparse2);

        System.out.println(
                "The compact matrix 2 is ...");
        print(packed2);

        int[][] unpacked2 = unpack(packed2);

//        System.out.println(
//                "The sparse matrix 2 again is ...");
//        print(unpacked2);

        int[][] packedAddition;
        if (sparse1.length == sparse2.length && 
                sparse1[0].length == sparse2[0].length) {
            packedAddition = addPackedMatrixes(packed1, packed2);
            System.out.println(
                    "The packed addition is ...");
            print(packedAddition);

            int[][] unpackedAdditon = unpack(packedAddition);

            System.out.println(
                    "The sparse addition matrix is ...");
            print(unpackedAdditon);
        } else {
            System.out.println("Matrixes cannot be add it");
        }
    }
}
