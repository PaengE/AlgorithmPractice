import java.util.HashMap;

public class A {
    public static void main(String[] args) {
        int arr = 0;
        int[] arr1 = new int[5];
//        int[] arr2 = arr1.clone();
        int[][] arr2 = new int[2][5];

        System.out.println("arr = " + arr);
        System.out.println("arr1 = " );
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
        System.out.println("arr2 = ");
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                System.out.print(arr2[i][j] + " ");
            }
            System.out.println();
        }

        tmp(arr, arr1, arr2);
        int[] a = new int[2];

        int[] b = a.clone();

        System.out.println();
        System.out.println("arr = " + arr);
        System.out.println("arr1 = " );
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
        System.out.println("arr2 = ");
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                System.out.print(arr2[i][j] + " ");
            }
            System.out.println();
        }



    }

    static void tmp(int a1, int[] a2, int[][] a3) {
        a1 = 2;
        a2[1] = 2;
        a3[1][1] = 2;
    }
}
