import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.4902: 삼각형의 값
 *  Hint: 누적합 + 구현
 */

public class BOJ4902 {
    static int n;
    static int[][] arr, preSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = "";
        int tc = 1;
        StringBuilder sb = new StringBuilder();
        while (!(s = br.readLine()).equals("0")) {
            StringTokenizer st = new StringTokenizer(s);
            n = Integer.parseInt(st.nextToken());
            arr = new int[n + 1][2 * n];
            preSum = new int[n + 1][2 * n];

            // preSum 구성
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= 2 * i - 1; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    preSum[i][j] = arr[i][j] + preSum[i][j - 1];
                }
            }
            int max = Integer.MIN_VALUE;

            // 정삼각형 부분 (i, j)를 위쪽 꼭지점으로 함
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= 2 * i - 1; j += 2) {
                    // (i, j)를 위 꼭지점으로 하여 만들 수 있는 최대 정삼각형의 크기 = n - i
                    for (int k = 0, triangleSum = 0; k < n - i + 1; k++) {    // (i + k) 크기의 삼각형 합
                        triangleSum += preSum[i + k][j + 2 * k] - preSum[i + k][j - 1];
                        max = Math.max(max, triangleSum);
                    }
                }
            }

            // 역삼각형 부분 (i, j)를 아래쪽 꼭지점으로 함
            for (int i = n; i >= 1; i--) {
                for (int j = 2; j <= 2 * i - 1; j += 2) {
                    // (i, j) 를 아래 꼭지점으로 하여 만들 수 있는 최대 삼각형의 크기 = min(j / 2, i - j / 2)
                    for (int k = 0, triangleSum = 0; k < Math.min(j / 2, i - j / 2); k++) {   //
                        triangleSum += preSum[i - k][j] - preSum[i - k][j - 2 * k - 1];
                        max = Math.max(max, triangleSum);
                    }
                }
            }

            sb.append(tc++ + ". " + max + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
