import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.18119: 단어 암기
 *  URL: https://www.acmicpc.net/problem/18119
 *  Hint: 비트마스킹 or  브루트포스 (여기서는 비트마스킹)
 */

public class BOJ18119 {
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int alpha = (1 << 27) - 1;  // 처음엔 모든 알파벳을 알고있음을 표시
        int[] words = new int[n];
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            for (char c : word.toCharArray()) {
                words[i] |= 1 << (c - 'a');
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int x = st.nextToken().charAt(0) - 'a';

            if (o == 1) {   //  잊을 알파벳을 지움
                alpha &= ~(1 << x);
            } else {    // 알파벳을 다시 기억함
                alpha |= (1 << x);
            }

            int res = 0;
            for (int word : words) {
                // 단어와 현재 알고 있는 알파벳들을 and 연산을 취했을 때 단어 값과 같으면
                // 알고 있는 단어란 뜻
                if ((alpha & word) == word) {
                    res++;
                }
            }
            sb.append(res + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}

// 시간초과 코드
/**
public class BOJ18119 {
    static int n, m;
    static String[] strings;
    static HashSet<String> set; // 잊어버린 알파벳만 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        strings = new String[n];
        set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            strings[i] = br.readLine();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String o = st.nextToken();
            String x = st.nextToken();

            if (o.equals("1")) {
                set.add(x);
            } else {
                set.remove(x);
            }

            int res = 0;
            for (int j = 0; j < n; j++) {
                for (String t : set) {
                    if (strings[j].indexOf(t) != -1) {
                        res++;
                        break;
                    }
                }
            }
            sb.append((n - res) + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
*/