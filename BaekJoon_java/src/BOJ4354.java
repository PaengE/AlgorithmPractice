import java.io.*;

/**
 *  No.4354: 문자열 제곱
 *  URL: https://www.acmicpc.net/problem/4354
 *  Hint: KMP 알고리즘의 Failure Function을 활용하는 문제 1
 */

public class BOJ4354 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = br.readLine();
            if (str.equals(".")) {
                break;
            }

            int[] pi = getPi(str);
            if (str.length() % (str.length() - pi[str.length() - 1]) != 0) {
                sb.append("1\n");
            } else {
                sb.append((str.length() / (str.length() - pi[str.length() - 1])) + "\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int[] getPi(String p) {
        int length = p.length();
        int[] pi = new int[length];
        int j = 0;

        for (int i = 1; i < length; i++) {
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
