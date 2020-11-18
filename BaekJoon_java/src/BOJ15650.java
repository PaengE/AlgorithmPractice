/*
    no.15650 : N and M(2)
    hint : 사용한 것은 true, 사용이 끝난 것은 false, 이전 값보다 큰 값들만 사용
 */

import java.util.Scanner;

public class BOJ15650 {

    static int n, m;
    static int[] arr;
    static boolean[] flag;

    static void solution(int idx){
        if(idx > m){
            for(int i = 1; i <= m; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        } else {
            for (int j = 1; j <= n; j++){
                if(!flag[j-1]){
                    if(arr[idx-1] < j){
                        arr[idx] = j;
                        flag[j-1] = true;
                        solution(idx + 1);
                        flag[j-1] = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[m+1];
        flag = new boolean[n+1];

        solution(1);

        sc.close();
    }
}
