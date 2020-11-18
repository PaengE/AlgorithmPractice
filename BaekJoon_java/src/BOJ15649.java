/*
    no.15649 : N and M(1)
    hint : 사용한 것은 true, 사용이 끝난 것은 false
 */

import java.util.Scanner;

public class BOJ15649 {

    static int n, m;
    static int[] arr;
    static boolean[] flag;

    static void solution(int idx){
        if(idx == m){
            for(int i = 0; i < m; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        } else {
            for (int j = 1; j <= n; j++){
                if(!flag[j-1]){
                    arr[idx] = j;
                    flag[j-1] = true;
                    solution(idx + 1);
                    flag[j-1] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[m];
        flag = new boolean[n];

        solution(0);

        sc.close();
    }
}