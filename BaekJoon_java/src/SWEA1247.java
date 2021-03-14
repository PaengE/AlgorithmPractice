import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  Difficulty: D5
 *  URL: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD&categoryId=AV15OZ4qAPICFAYD&categoryType=CODE&problemTitle=1247&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1&&&&&&&&&
 */

public class SWEA1247 {
    static int n, cx, cy, hx, hy, ans;
    static boolean[] used;
    static int[][] customer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            customer = new int[n][2];
            used = new boolean[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            cx = Integer.parseInt(st.nextToken());
            cy = Integer.parseInt(st.nextToken());
            hx = Integer.parseInt(st.nextToken());
            hy = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;

            ArrayList<Integer> arr = new ArrayList<>();
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                customer[i][0] = Integer.parseInt(st.nextToken());
                customer[i][1] = Integer.parseInt(st.nextToken());
                arr.add(i);
            }
            permutation(arr, res, n, n, 0);

            sb.append("#" + tc + " " + ans + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void permutation(ArrayList<Integer> arr, int[] res, int n, int r, int depth) {
        if (depth == r) {
            int total = getDist(cx, cy, customer[res[0]][0], customer[res[0]][1]);
            for (int i = 0; i < res.length - 1; i++) {
                total += getDist(customer[res[i]][0], customer[res[i]][1], customer[res[i + 1]][0], customer[res[i + 1]][1]);
            }
            total += getDist(customer[res[n - 1]][0], customer[res[n - 1]][1], hx, hy);
            ans = Math.min(ans, total);
            return;
        }

        for (int i = 0; i < n - depth; i++) {
            res[depth] = arr.remove(i);
            permutation(arr, res, n, r, depth + 1);
            arr.add(i, res[depth]);
        }
    }

    static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
