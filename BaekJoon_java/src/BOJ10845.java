import java.io.*;
import java.util.*;

public class BOJ10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deq = new ArrayDeque<>();

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            if (s.equals("push")) {
                deq.offerLast(Integer.parseInt(st.nextToken()));
            } else if (s.equals("pop")) {
                if (deq.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(deq.poll() + "\n");
                }
            } else if (s.equals("size")) {
                sb.append(deq.size() + "\n");
            } else if (s.equals("empty")) {
                if (deq.isEmpty()) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            } else if (s.equals("front")) {
                if (deq.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(deq.peekFirst() + "\n");
                }
            } else if (s.equals("back")) {
                if (deq.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(deq.peekLast() + "\n");
                }
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
