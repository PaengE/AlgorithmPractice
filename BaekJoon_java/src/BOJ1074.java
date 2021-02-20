import java.io.*;
import java.util.StringTokenizer;

public class BOJ1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int t = (int) Math.pow(2, n);
        int ans = find(n, c, r, t);

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static int find(int n, int c, int r, int t) {
        // 가로 세로를 반으로 나눔
        int x = t / 2;
        int y = t / 2;
        int ans = 0;

        while (n-- > 0) {
            int half = (int) (Math.pow(2, n) / 2);
            int idx = (int) Math.pow(4, n);

            if (r < y && c < x) {   // 좌상
                x -= half;
                y -= half;
            } else if (r < y && c >= x) {   // 우상
                x += half;
                y -= half;
                ans += idx;
            } else if (r >= y && c < x) {   // 좌하
                x -= half;
                y += half;
                ans += idx * 2;
            } else {    // 우하
                x += half;
                y += half;
                ans += idx * 3;
            }
        }
        return ans;
    }
}
