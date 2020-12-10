import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.1717: 집합의 표현
 *  URL: https://www.acmicpc.net/problem/1717
 *  Description: 유니온 파인드(disjoint set)에 대해 알아보는 문제
 *  Hint: union-find problem
 */

public class BOJ1717 {
    static int n, m;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 0) {
                union(a, b);
            } else if (op == 1) {
                if (find(a) == find(b)) {
                    sb.append("YES");
                } else {
                    sb.append("NO");
                }
                sb.append("\n");
            }
        }
        bw.write(sb.toString());
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
        if (a < b) {
            parents[find(b)] = find(a);
        } else if (a > b) {
            parents[find(a)] = find(b);
        }
    }
}
