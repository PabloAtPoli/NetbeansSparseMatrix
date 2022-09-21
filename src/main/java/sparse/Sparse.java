package sparse;

import java.util.Scanner;

public class Sparse {

    private static int array[][];

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

    public static void main(String args[]) {

        read();

        if (isSparse()) {
            System.out.println("the matrix is sparse");
        } else {
            System.out.println("the matrix is not sparse");
        }
    }
}
