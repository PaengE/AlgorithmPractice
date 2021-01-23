import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.3273: 두 수의 합
 *  hint: 풀이방법은 여러가지이나 투 포인터 연습문제
 */

public class BOJ3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int m = Integer.parseInt(br.readLine());

        int count = 0;
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            if (arr[start] + arr[end] > m) {
                end--;
            } else if (arr[start] + arr[end] < m) {
                start++;
            } else {
                count++;
                start++;
                end--;
            }
        }
        bw.write(String.valueOf(count));
        bw.close();
        br.close();
    }
}
