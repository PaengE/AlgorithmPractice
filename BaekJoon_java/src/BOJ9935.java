import java.io.*;

/**
 *  No.9935: 문자열 폭발
 *  URL: https://www.acmicpc.net/problem/9935
 *  Hint: 문자열 처리 (replace)는 메모리초과(시간초과)
 *        스택 or char[] 사용해야함
 */

public class BOJ9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String bomb = br.readLine();

        String ans = exploit(str, bomb);
        bw.write(ans.length() == 0 ? "FRULA" : ans);
        bw.close();
        br.close();
    }

    static String exploit(String str, String bomb) {
        char[] res = new char[str.length()];
        int idx = 0;

        for (int i = 0; i < str.length(); i++) {
            res[idx] = str.charAt(i);
            if (isBomb(res, idx, bomb)) {
                idx -= bomb.length();
            }
            idx++;
        }

        return String.valueOf(res, 0, idx); // 0 ~ idx 사이의 값만
    }

    static boolean isBomb(char[] res, int idx, String bomb) {
        if (idx < bomb.length() - 1) {
            return false;
        }

        for (int i = 0; i < bomb.length(); i++) {
            if (bomb.charAt(i) != res[idx - bomb.length() + 1 + i]) {
                return false;
            }
        }

        return true;
    }
}