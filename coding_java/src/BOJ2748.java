import java.util.Scanner;

/*
    no.2748 : fibonacci (2)
 */

public class BOJ2748 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long a = 0;
        long b = 1;
        long sum = 0;
        if (n == 0 || n == 1){
            System.out.println(n);
        } else {
            for(int i = 1; i < n; i++){
                sum = a + b;
                a = b;
                b = sum;
            }
            System.out.println(sum);
        }
    }
}
