import java.io.*;

public class SWEA1954 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int j = 1; j <= t; j++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];

            int idx = 0;
            int cnt = 1;
            while (idx < n) {
                // 오른쪽 방향
                for (int i = idx; i <= n - 2; i++) {
                    arr[idx][i] = cnt++;
                }

                // 아래쪽 방향
                for (int i = idx; i < n - 1; i++) {
                    arr[i][n - 1] = cnt++;
                }

                // 왼쪽 방향
                for (int i = n - 1; i >= idx + 1 ; i--) {
                    arr[n - 1][i] = cnt++;
                }

                // 위쪽 방향
                for (int i = n - 1; i >= idx + 1; i--) {
                    arr[i][idx] = cnt++;
                }

                idx++;
                n--;
            }
    
            // n이 홀수일때 정가운데 예외처리
            if (idx != n) {
                arr[n][n] = cnt;
            }

            sb.append("#" + j + "\n");
            for (int[] a : arr) {
                for (int b : a) {
                    sb.append(b + " ");
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
