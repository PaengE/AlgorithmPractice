import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.20040: 사이클 게임
 *  Hint: Union-Find 알고리즘 활용
 */

public class BOJ20040 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (findSet(a) != findSet(b)) {
                union(a, b);
            } else {
                ans = i + 1;
                break;
            }
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static int findSet(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = findSet(parent[x]);
        }
    }

    static void union(int x, int y) {
        if (x < y) {
            parent[findSet(y)] = parent[findSet(x)];
        } else {
            parent[findSet(x)] = parent[findSet(y)];
        }
    }
}
