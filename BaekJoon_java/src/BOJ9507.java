import java.io.*;

/**
 *  No.9507: Generations of Tribbles
 *  URL: https://www.acmicpc.net/problem/9507
 */

public class BOJ9507 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        long[] fibo = new long[68];
        StringBuilder sb = new StringBuilder();

        fibo[0] = 1;
        fibo[1] = 1;
        fibo[2] = 2;
        fibo[3] = 4;
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            if (fibo[n] != 0) {
                sb.append(fibo[n]).append("\n");
                continue;
            } else {
                for (int j = 4; j <= n; j++) {
                    if(fibo[j] == 0) {
                        fibo[j] = fibo[j - 1] + fibo[j - 2] + fibo[j - 3] + fibo[j - 4];
                    }
                }
            }
            sb.append(fibo[n]).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
