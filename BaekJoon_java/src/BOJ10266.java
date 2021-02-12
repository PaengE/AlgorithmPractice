import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.10266: 시계 사진들
 *  URL: https://www.acmicpc.net/problem/10266
 *  Hint: KMP 알고리즘을 응용한 문제
 */

public class BOJ10266 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] c1 = new int[720000];
        int[] c2 = new int[360000];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(st1.nextToken());
            c1[t] = 1;
            c1[t + 360000] = 1;
        }
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            c2[Integer.parseInt(st2.nextToken())] = 1;
        }

        bw.write(kmp(c1, c2));

        bw.close();
        br.close();
    }

    static String kmp(int[] target, int[] pattern) {
        int[] pi = getPi(pattern);

        int j = 0;
        for (int i = 0; i < target.length; i++) {
            while (j > 0 && target[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (target[i] == pattern[j]) {
                if (j == pattern.length - 1) {
                    return "possible";
                } else {
                    ++j;
                }
            }
        }
        return "impossible";
    }

    static int[] getPi(int[] pattern) {
        int[] pi = new int[pattern.length];

        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}
