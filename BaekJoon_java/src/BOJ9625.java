import java.io.*;

/**
 *  No.9625: BABBA
 *  URL: https://www.acmicpc.net/problem/9625
 */

public class BOJ9625 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());
        int[] a = new int[k + 1];
        int[] b = new int[k + 1];


        a[0] = 1;
        a[1] = 0;
        b[0] = 0;
        b[1] = 1;
        for (int i = 2; i <= k; i++) {
            a[i] = a[i - 2] + a[i - 1];
            b[i] = b[i - 2] + b[i - 1];
        }

        bw.write(a[k] + " " + b[k]);
        bw.close();
        br.close();
    }
}
