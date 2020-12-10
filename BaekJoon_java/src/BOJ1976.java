import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.1976: 여행 가자
 *  URL: https://www.acmicpc.net/problem/1976
 *  Hint: union-find (플로이드 마샬 로도 풀이 가능)
 */

public class BOJ1976 {
    static int[] parents;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(i, j);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int p = find(Integer.parseInt(st.nextToken()));
        boolean ans = true;
        while (st.hasMoreTokens()) {
            if (p != find(Integer.parseInt(st.nextToken()))) {
                ans = false;
                break;
            }
        }

        if (ans) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }

        bw.close();
        br.close();
    }
    static int find(int x) {
        if (parents[x] == x) {
            return x;
        } else {
            return parents[x] = find(parents[x]);
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parents[b] = a;
        } else if (a > b) {
            parents[a] = b;
        }
    }
}
