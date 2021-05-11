import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *  No.1062: 가르침
 *  URL: https://www.acmicpc.net/problem/1062
 *  Hint: 크기 26 배열 + 조합
 */

public class BOJ1062 {
    static int n, k, ans;
    static int size;
    static ArrayList<Character> usedAlphabet;
    static String[] str;
    static HashSet<Character> set;
    static boolean[] used = new boolean[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        set = new HashSet<>();  // "antic"을 제외한 알파벳 중 사용된 알파벳들을 저장
        str = new String[n];    // 앞4글자 뒤4글자를 지운 문자열
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            str[i] = s.substring(4, s.length() - 4);
            for (int j = 0; j < str[i].length(); j++) {
                set.add(str[i].charAt(j));
            }
        }

        init();

        // k가 5보다 작으면 "antic"을 표현할 수없음
        // k == 26 이면 모든 글자를 배울 수 있으므로 모든 글자를 표현 가능
        // size는 "antic"을 제외하고 사용한 알파벳의 개수인데 그게 k-5 보다 작거나 같으면 모든 글자 표현 가능
        if (k < 5 || k == 26 || size <= k - 5) {
            bw.write(k == 26 || size <= k - 5 ? String.valueOf(n) : "0");
            bw.close();
            br.close();
            return;
        }

        combination(5, 0);
        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static void combination(int t, int idx) {
        if (t == k) {
            int cnt = 0;
            // 해당 문자열이 알고 있는 문자들로 표현 가능한지를 체크
            for (int i = 0; i < str.length; i++) {
                boolean flag = true;
                for (int j = 0; j < str[i].length(); j++) {
                    if (!used[str[i].charAt(j) - 'a']) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
            return;
        }

        for (int i = idx; i < size; i++) {
            used[usedAlphabet.get(i) - 'a'] = true;
            combination(t + 1, i + 1);
            used[usedAlphabet.get(i) - 'a'] = false;
        }
    }

    // "antic"을 제외하고 사용한 알파벳의 집합인 usedAlphabet 리스트를 구성, "antic"을 사용했다고 used배열에 표시
    static void init() {
        set.remove('a');
        set.remove('n');
        set.remove('t');
        set.remove('i');
        set.remove('c');

        usedAlphabet = new ArrayList<>(set);
        size = usedAlphabet.size();

        used[0] = true;
        used['n' - 'a'] = true;
        used['t' - 'a'] = true;
        used['i' - 'a'] = true;
        used['c' - 'a'] = true;
    }
}