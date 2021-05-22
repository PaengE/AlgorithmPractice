import java.io.*;

/**
 *  No.12094: A와 B
 *  Hint: Greedy
 */

public class BOJ12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        String t = br.readLine();

        boolean flag = delete(s, t);
        if (flag) {
            bw.write("1");
        } else {
            bw.write("0");
        }

        bw.close();
        br.close();
    }

    static boolean delete(String s, String t) {
        StringBuilder sb = new StringBuilder(t);

        // 비교 문자열과 길이가 같아질 때까지
        while (s.length() < sb.length()) {
            char c = sb.charAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);   // 마지막 문자를 삭제

            if (c == 'B') { // 마지막 문자가 B였다면 문자열 뒤집기
                sb.reverse();
            }
        }

        // 길이가 서로 같아졌을 때 비교
        if (s.equals(sb.toString())) {
            return true;
        } else {
            return false;
        }
    }
}
