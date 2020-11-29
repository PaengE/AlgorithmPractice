import java.io.*;

/**
 *  No.2011: 암호코드
 *  URL: https://www.acmicpc.net/problem/2011
 */

public class BOJ2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int length = s.length();

        long[] dp = new long[length + 1];
        dp[0] = dp[1] = 1;

        if (s.charAt(0) == '0') {
            bw.write("0");
        } else if (s.charAt(length - 1) == '0' && s.charAt(length - 2) != '1' && s.charAt(length - 2) != '2') {
            bw.write("0");
        } else {
            for (int i = 2; i <= length; i++) {
                int tmp = Integer.parseInt(s.charAt(i - 1) + "");
                if (tmp > 0) dp[i] = dp[i - 1] % 1000000;

                tmp += Integer.parseInt(s.charAt(i - 2) + "") * 10;
                if (10 <= tmp && tmp <= 26) dp[i] = (dp[i] + dp[i - 2]) % 1000000;
            }
            bw.write(String.valueOf(dp[length]));
        }

        bw.close();
        br.close();
    }
}
