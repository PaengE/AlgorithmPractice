import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  No.2961: 도영이가 만든 맛있는 음식
 *  URL: https://www.acmicpc.net/problem/2961
 *  Hint: 조합(Combination)을 활용한 문제
 */

public class BOJ2961 {
    static int n, diff;
    static int[] sour, bitter;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        sour = new int[n];
        bitter = new int[n];
        diff = Integer.MAX_VALUE;
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sour[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
            arr.add(i);
        }

        for (int i = 1; i <= n; i++) {
            makeCombination(arr, res, n, i, 0);
        }

        bw.write(String.valueOf(diff));
        bw.close();
        br.close();
    }

    static void makeCombination(ArrayList<Integer> arr, ArrayList<Integer> res, int n, int r, int idx) {
        if (r == 0) {
            int s = 1, b = 0;
            for (int i : res) {
                s *= sour[i];
                b += bitter[i];
            }
            diff = Math.min(diff, Math.abs(s - b));
            return;
        }

        for (int i = idx; i < n; i++) {
            res.add(arr.get(i));
            makeCombination(arr, res, n, r - 1, i + 1);
            res.remove(res.size() - 1);

        }
    }
}
