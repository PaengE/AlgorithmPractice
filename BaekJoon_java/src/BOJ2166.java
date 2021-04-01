import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.2166: 다각형의 면적
 *  URL: https://www.acmicpc.net/problem/2166
 *  Hint: ccw 공식 이용
 *
 *  세 점 (x1, y1), (x2, y2), (x3, y3) 으로 이루어진 삼각형의 면적
 *  = 1/2 ((x1y2 + x2y3 + x3y1) - (x1y3 + x2y1 + x3y2))
 */

public class BOJ2166 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        long[][] arr = new long[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());   // x 좌표
            arr[i][1] = Integer.parseInt(st.nextToken());   // y 좌표
        }

        long ans = 0;
        for (int i = 1; i < n - 1; i++) {
            ans += calArea(arr[0][0], arr[0][1], arr[i][0], arr[i][1], arr[i + 1][0], arr[i + 1][1]);
        }

        ans = Math.abs(ans);
        if (ans % 2 == 1) {
            bw.write((ans / 2) + ".5");
        } else {
            bw.write((ans / 2) + ".0");
        }

        bw.close();
        br.close();
    }

    static long calArea(long x1, long y1, long x2, long y2, long x3, long y3) {
        return (x1 * y2 + x2 * y3 + x3 * y1) - (x1 * y3 + x2 * y1 + x3 * y2);
    }
}
