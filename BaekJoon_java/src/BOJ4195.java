import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *  No.4195: 친구 네트워크
 *  URL: https://www.acmicpc.net/problem/4195
 *  Description: 유니온 파인드에 집합의 크기를 구하는 기능을 넣는 문제
 *  Hint: union-find
 */

public class BOJ4195 {
    static int[] p, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st;
            StringBuilder sb = new StringBuilder();

            int f = Integer.parseInt(br.readLine());
            p = new int[f * 2];
            cnt = new int[f * 2];
            Arrays.fill(cnt, 1);

            HashMap<String, Integer> map = new HashMap<>();

            for (int i = 0, idx = 0; i < f; i++) {
                st = new StringTokenizer(br.readLine());
                String s1 = st.nextToken();
                String s2 = st.nextToken();

                if (!map.containsKey(s1)) {
                    map.put(s1, idx);
                    p[idx] = idx++;
                }

                if (!map.containsKey(s2)) {
                    map.put(s2, idx);
                    p[idx] = idx++;
                }

                int a = map.get(s1);
                int b = map.get(s2);
                union(a, b);
                sb.append(cnt[find(a)]).append("\n");
            }
            bw.write(sb.toString());
        }
        bw.close();
        br.close();
    }
    static int find(int x) {
        if (p[x] == x) {
            return x;
        } else {
            return p[x] = find(p[x]);
        }
    }

    // 서로 다른 두 집합을 합치고 각자 집합의 개수를 합침
    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        /* 문제에서 친구관계가 생성된 순서대로 들어온다 가정 하였으므로
        *  항상 a < b 가 성립하므로 밑의 조건식을 없애고 a < b 로만 생각해도됨 */
        if (a < b) {
            p[b] = a;
            cnt[a] += cnt[b];
        } else if (a > b) {
            p[a] = b;
            cnt[b] += cnt[a];
        }
    }
}
