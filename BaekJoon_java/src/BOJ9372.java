import java.io.*;
import java.util.StringTokenizer;

/**
 * No.9372: 상근이의 여행
 * URL: https://www.acmicpc.net/problem/9372
 */

public class BOJ9372 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            for (int j = 0; j < m; j++) {
                br.readLine();
            }

            bw.write(String.valueOf(n - 1) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
