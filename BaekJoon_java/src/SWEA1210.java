import java.io.*;
import java.util.StringTokenizer;

public class SWEA1210 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = 10;
        while (t-- > 0) {
            int tc = Integer.parseInt(br.readLine());
            int[][] arr = new int[100][100];
            int cur = -1;
            for (int i = 0; i < 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 2) {
                        cur = j;
                    }
                }
            }

            int lv = 99;
            while (lv != 0) {
                if (cur - 1 >= 0 && arr[lv][cur - 1] == 1) {
                    while (cur - 1 >= 0 && arr[lv][cur - 1] == 1) {
                        cur--;
                    }
                } else if (cur + 1 < 100 && arr[lv][cur + 1] == 1) {
                    while (cur + 1 < 100 && arr[lv][cur + 1] == 1) {
                        cur++;
                    }
                }
                lv--;
            }

            bw.write("#" + tc + " " + cur + "\n");
        }
        bw.close();
        br.close();
    }
}
