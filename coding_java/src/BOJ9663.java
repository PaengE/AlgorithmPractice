/*
    no.9663: N-Queen
    hint: 위에서부터 퀸을 놓아보고 놓을 수 있는지(일직선 상에 있거나 대각선에 있거나) 검사 후
        놓을 수 있으면 다음 줄로, 놓을 수 없으면 backtracking
 */
import java.io.*;

public class BOJ9663 {
    static int n, count = 0;
    static int[] arr;
    static BufferedReader br;

    static void backtracking(int lv) throws IOException{
        if (lv == n){
            count++;
        } else {
            for(int i = 0; i < n; i++){
                arr[lv] = i;
                if (isPossible(lv)){
                    backtracking(lv + 1);
                }
            }
        }
    }
    static boolean isPossible(int lv){
        for (int i = 0; i < lv; i++){
            if (arr[i] == arr[lv] || Math.abs(arr[i] - arr[lv]) == Math.abs(lv - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        n = Integer.parseInt(s);
        arr = new int[n];

        backtracking(0);

        System.out.println(count);
        br.close();
    }
}
