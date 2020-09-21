import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * no.11866 : 요세푸스 문제 0
 * hint : LinkedList 를 이용한 Queue 사용
 */

public class BOJ11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        Queue<Integer> q = new LinkedList<Integer>();

        for (int i = 1; i <= n; i++) {
            q.offer(i);
        }
        bw.write("<");
        while (q.size() >= 2) {
            for (int i = 0; i < k - 1; i++) {
                q.offer(q.poll());
            }
            bw.write(q.poll() + ", ");
        }

        bw.write(q.poll() + ">");

        bw.flush();
        bw.close();
        br.close();
    }
}
