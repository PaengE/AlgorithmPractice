/*
    no.9663: N-Queen
 */
import java.io.*;

public class BOJ9663 {
    static int n, count = 0;
    static int[] arr;
    static BufferedReader br;
//    static BufferedWriter bw;

    static void backtracking(int lv) throws IOException{
        if (lv == n){
//            for (int i = 0; i < n; i++){
//                bw.write(arr[i] + " ");
//            }
//            bw.write("\n");
//            bw.flush();
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
//        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        n = Integer.parseInt(s);
        arr = new int[n];

        backtracking(0);

        br.close();
//        bw.close();

        System.out.println(count);
    }
}
