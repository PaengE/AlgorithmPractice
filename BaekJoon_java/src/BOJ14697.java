import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.14697: 방 배정하기
 *  URL: https://www.acmicpc.net/problem/14697
 */

public class BOJ14697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int t1 = 0, t2, t3;
        for(int i = 0; t1 <= n; i++) {
            t1 = a * i;
            t2 = 0;
            for(int j = 0; t2 <= n; j++) {
                t2 = b * j;
                t3 = 0;
                for(int k = 0; t3 <= n; k++) {
                    t3 = c * k;
                    if(t1 + t2 + t3 == n) {
                        bw.write("1");
                        bw.close();
                        br.close();
                        System.exit(0);
                    }
                }
            }
        }
        bw.write("0");
        bw.close();
        br.close();
    }
}
