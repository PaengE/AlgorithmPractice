import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.1261: 치킨 배달
 *  URL: https://www.acmicpc.net/problem/15686
 *  Hint: 조합(Combination) & 완전탐색
 */

public class BOJ15686 {
    static int n, m, ans;
    static int[][] map;
    static ArrayList<int[]> store, house;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        map = new int[n][n];
        store = new ArrayList<>();
        house = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();

        int t = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    house.add(new int[]{i, j});
                } else if (map[i][j] == 2) {
                    store.add(new int[]{i, j});
                    arr.add(t++);
                }
            }
        }

        combination(arr, res, store.size(), m, 0);

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();

    }

    static void combination(ArrayList<Integer> arr, ArrayList<Integer> res, int n, int r, int idx) {
        if (r == 0) {
            ans = Math.min(ans, calculateChickenDistance(res));
            return;
        }

        for (int i = idx; i < n; i++) {
            res.add(i);
            combination(arr, res, n, r - 1, i + 1);
            res.remove(res.size() - 1);
        }
    }

    static int calculateChickenDistance(ArrayList<Integer> res) {
        int[] sum = new int[house.size()];
        Arrays.fill(sum, Integer.MAX_VALUE);
        for (int i = 0; i < res.size(); i++) {
            for (int j = 0; j < house.size(); j++) {
                sum[j] = Math.min(sum[j], getDistance(store.get(res.get(i))[0], store.get(res.get(i))[1], house.get(j)[0], house.get(j)[1]));
            }
        }
        return Arrays.stream(sum).sum();
    }

    static int getDistance(int sx, int sy, int hx, int hy) {
        return Math.abs(sx - hx) + Math.abs(sy - hy);
    }
}
