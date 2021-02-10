import java.io.*;

/**
 *  No.1305: 광고
 *  URL: https://www.acmicpc.net/problem/1305
 *  Hint: KMP의 failure function을 활용하는 문제 2
 */

public class BOJ1305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int L = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int[] pi = getPi(str);

        bw.write(String.valueOf(L - pi[str.length() - 1]));
        bw.close();
        br.close();
    }

    static int[] getPi(String p) {
        int[] pi = new int[p.length()];

        int j = 0;
        for (int i = 1; i < p.length(); i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = pi[j - 1];
            }

            if (p.charAt(i) == p.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}
