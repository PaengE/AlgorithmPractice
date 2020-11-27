import java.io.*;

/**
 *  No.9655: 돌 게임
 *  URL: https://www.acmicpc.net/problem/9655
 */

public class BOJ9655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        if (n % 2 == 1) {
            bw.write("SK");
        } else {
            bw.write("CY");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
