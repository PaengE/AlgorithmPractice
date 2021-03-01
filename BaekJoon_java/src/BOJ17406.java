import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.17406: 배열 돌리기 4
 *  URL: https://www.acmicpc.net/problem/17406
 *  Hint: 순열 + 배열회전
 */

public class BOJ17406 {
    static int[][] ori, copy;
    static int N, M, K, ans;
    static String[] op;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ori = new int[N][M];
        copy = new int[N][M];
        ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ori[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = ori[i][j];
            }
        }

        op = new String[K];
        ArrayList<Integer> arr = new ArrayList<>();
        int[] res = new int[K];
        for (int i = 0; i < K; i++) {
            op[i] = br.readLine();
            arr.add(i);
        }

        permutation(arr, res, 0, K, K);

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    // 순열 구하기
    static void permutation(ArrayList<Integer> arr, int[] res, int depth, int n, int r) {
        if (depth == r) {
            rotate(res);
            ans = Math.min(ans, calcValue());
            initCopyArray();
            return;
        }

        for (int i = 0; i < n - depth; i++) {
            res[depth] = arr.remove(i);
            permutation(arr, res, depth + 1, n, r);
            arr.add(i, res[depth]);
        }
    }

    // 배열 회전
    static void rotate(int[] list) {
        for (int x : list) {
            String[] str = op[x].split(" ");
            int r = Integer.parseInt(str[0]) - 1;
            int c = Integer.parseInt(str[1]) - 1;
            int s = Integer.parseInt(str[2]);

            while (s > 0) {
                int t = copy[r - s][c - s];

                // up
                for (int i = r - s; i < r + s; i++) {
                    copy[i][c - s] = copy[i + 1][c - s];
                }
                // left
                for (int i = c - s; i < c + s; i++) {
                    copy[r + s][i] = copy[r + s][i + 1];
                }
                // down
                for (int i = r + s; i > r - s; i--) {
                    copy[i][c + s] = copy[i - 1][c + s];
                }
                // right
                for (int i = c + s; i > c - s + 1; i--) {
                    copy[r - s][i] = copy[r - s][i - 1];
                }

                copy[r - s][c - s + 1] = t;
                s--;
            }
        }
    }

    // 배열의 값 구하기
    static int calcValue() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < copy.length; i++) {
            min = Math.min(min, Arrays.stream(copy[i]).sum());
        }
        return min;
    }

    // copy 배열 초기화
    static void initCopyArray() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = ori[i][j];
            }
        }
    }
}
