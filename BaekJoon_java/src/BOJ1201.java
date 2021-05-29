import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.1201: NMK
 *  Hint: Greedy
 */

public class BOJ1201 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        bw.write(solve(n, m, k));
        bw.close();
        br.close();
    }

    static String solve(int n, int m, int k) {
        if (m + k - 1 > n || m * k < n) {
            return "-1";
        }

        int[] groupSize = new int[m];
        groupSize[0] = k;   // 첫번째 그룹의 크기는 k
        n -= k;

        if (m - 1 > 0) {
            int div = n / (m - 1);  // 나머지 그룹의 기본 크기
            int remain = n % (m - 1);   // 균등하게 나누어지지 못한 부분을 나머지 그룹에 재분배하기 위함

            for (int i = 1; i < m; i++) {
                if (remain-- > 0) {
                    groupSize[i] = div + 1;
                } else {
                    groupSize[i] = div;
                }
            }
        }

        // 순열 구하기
        StringBuilder sb = new StringBuilder();
        int sIdx = 0, eIdx = 0;
        for (int i = 0; i < m; i++) {
            sIdx = eIdx;
            eIdx += groupSize[i];
            for (int j = eIdx - 1; j >= eIdx - groupSize[i]; j--) {
                sb.append((j + 1) + " ");
            }
        }

        return sb.toString();
    }
}
