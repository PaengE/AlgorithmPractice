import java.io.*;
import java.util.StringTokenizer;

public class BOJ18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        int minH = Integer.MAX_VALUE;
        int maxH = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minH = Math.min(minH, map[i][j]);
                maxH = Math.max(maxH, map[i][j]);
            }
        }

        int ansTime = Integer.MAX_VALUE;
        int ansHeight = 0;
        loop:
        for (int i = minH; i <= maxH; i++) {
            int block = b;
            int remove = 0;
            int add = 0;

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    int diffH = Math.abs(i - map[j][k]);
                    if (map[j][k] < i) {
                        add += diffH;
                    } else if (map[j][k] > i) {
                        remove += diffH;
                    }
                }
            }

            if (block + remove >= add) {
                int time = add + (remove * 2);
                if (ansTime >= time) {
                    ansTime = time;
                    ansHeight = i;
                }
            }
        }

        bw.write(ansTime + " " + ansHeight);
        bw.close();
        br.close();
    }
}
