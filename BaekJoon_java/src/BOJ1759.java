import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.1759: 암호 만들기
 *  URL: https://www.acmicpc.net/problem/1759
 *  Hint: Backtracking + 자모음 수 세기
 */

public class BOJ1759 {
    static int l, c;
    static char[] alpha, res;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        alpha = new char[c];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }
        res = new char[l];
        Arrays.sort(alpha);

        backtracking(0,0,0, 0);

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void backtracking(int index, int depth, int jaeum, int moeum) {
        if (depth == l) {
            if (moeum >= 1 && jaeum >= 2) {
                sb.append(res).append("\n");
            }
            return;
        }

        for (int i = index; i < c; i++) {
            res[depth] = alpha[i];
            if (isAEIOU(alpha[i])) {
                backtracking(i + 1, depth + 1, jaeum, moeum + 1);
            } else {
                backtracking(i + 1, depth + 1, jaeum + 1, moeum);
            }
        }
    }

    static boolean isAEIOU(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        return false;
    }
}
