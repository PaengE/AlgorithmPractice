import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
    no.11399 : ATM
    hint : 오름차순으로 정렬 후 더하기
 */
public class BOJ11399 {
    private static void greedy(int n, int[] arr){
        int time = arr[0];
        for(int i = 1; i < n; i++){
            arr[i] = arr[i-1] + arr[i];
            time = time + arr[i];
        }
        System.out.println(time);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] s = br.readLine().split(" ");

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        br.close();
        Arrays.sort(arr);

        greedy(n, arr);
    }
}
