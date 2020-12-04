import java.io.*;

/**
 *  No.9656: 돌 게임 2
 *  URL: https://www.acmicpc.net/problem/9656
 */

public class BOJ9656 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        if (n % 2 == 1) {
            bw.write("CY");
        } else {
            bw.write("SK");
        }

        bw.close();
        br.close();
    }
}
