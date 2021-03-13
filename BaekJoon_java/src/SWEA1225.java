import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  Difficulty: D3
 *  URL: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14uWl6AF0CFAYD&categoryId=AV14uWl6AF0CFAYD&categoryType=CODE&problemTitle=1225&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

public class SWEA1225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int tc = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            Queue<Integer> q = new LinkedList<>();
            for (int j = 0; j < 8; j++) {
                q.offer(Integer.parseInt(st.nextToken()));
            }

            loop:
            while (true) {
                for (int k = 1; k <= 5; k++) {
                    int num = q.poll();
                    num -= k;
                    if (num <= 0) {
                        break loop;
                    }
                    q.offer(num);
                }
            }

            sb.append("#" + tc + " ");
            q.forEach(a -> sb.append(a + " "));
            sb.append("0\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
