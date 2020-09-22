import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * no.10866 : 덱
 * hint : LinkedList 를 이용하여 Deque 활용
 */

public class BOJ10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new LinkedList<Integer>();

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            switch(s[0]){
                case "push_front":
                    deque.offerFirst(Integer.parseInt(s[1]));
                    break;
                case "push_back":
                    deque.offerLast(Integer.parseInt(s[1]));
                    break;
                case "pop_front":
                    if(deque.isEmpty())
                        bw.write("-1\n");
                    else
                        bw.write(deque.pollFirst() + "\n");
                    break;
                case "pop_back":
                    if(deque.isEmpty())
                        bw.write("-1\n");
                    else
                        bw.write(deque.pollLast() + "\n");
                    break;
                case "size":
                    bw.write(deque.size() + "\n");
                    break;
                case "empty":
                    if(deque.isEmpty())
                        bw.write("1\n");
                    else
                        bw.write("0\n");
                    break;
                case "front":
                    if(deque.isEmpty())
                        bw.write("-1\n");
                    else
                        bw.write(deque.peekFirst() + "\n");
                    break;
                case "back":
                    if(deque.isEmpty())
                        bw.write("-1\n");
                    else
                        bw.write(deque.peekLast() + "\n");
                    break;
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
