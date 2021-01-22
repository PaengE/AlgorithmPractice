import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {
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
        int start = 0, end = arr.length - 1;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int nearest = Integer.MAX_VALUE;

        while (start < end) {
            int abs = Math.abs(arr[start] + arr[end]);
            if (abs < nearest) {
                nearest = Math.min(nearest, abs);
                min = arr[start];
                max = arr[end];
            }

            if (arr[start] + arr[end] > 0) {
                end--;
            } else if (arr[start] + arr[end] < 0) {
                start++;
            } else {
                break;
            }
        }
        bw.write(min + " " + max);
        bw.close();
        br.close();
    }
}
