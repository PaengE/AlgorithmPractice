import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * no.18258 : 큐 2
 * hint : LinkedList 를 이용하여 Queue 사용
 */

public class BOJ18258 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Queue q = new LinkedList();
        int n = Integer.parseInt(br.readLine());
        int last = 0;

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");

            switch (s[0]){
                case "push":
                    last = Integer.parseInt(s[1]);
                    q.offer(last);
                    break;
                case "pop":
                    if(q.isEmpty())
                        bw.write("-1\n");
                    else{
                        bw.write(q.poll() + "\n");
                    }
                    break;
                case "size":
                    bw.write(q.size() + "\n");
                    break;
                case "empty":
                    if(q.isEmpty())
                        bw.write("1\n");
                    else
                        bw.write("0\n");
                    break;
                case "front":
                    if(q.isEmpty())
                        bw.write("-1\n");
                    else
                        bw.write(q.peek() + "\n");
                    break;
                case "back":
                    if(q.isEmpty())
                        bw.write("-1\n");
                    else
                        bw.write(last + "\n");
                    break;

            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
