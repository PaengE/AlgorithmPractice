import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * no.5430 : AC
 * hint : 출력에 신경 쓸 것...
 */

public class BOJ5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String[] p = br.readLine().split("");
            int n = Integer.parseInt(br.readLine());

            Deque<String> deq = new LinkedList<>();

            String[] s = br.readLine().replaceAll("[\\[\\]]", "").split(",");
            for (int j = 0; j < n; j++) {
                deq.offer(s[j]);
            }

            int start = 0;
            boolean check = true;
            for (int j = 0; j < p.length; j++) {
                if(check){
                    switch (p[j]){
                        case "R":
                            start++;
                            break;
                        case "D":
                            if(deq.isEmpty()){
                                bw.write("error\n");
                                check = false;
                            } else {
                                if(start % 2 == 0){
                                    deq.pollFirst();
                                } else {
                                    deq.pollLast();
                                }
                            }
                            break;
                    }
                }
            }
            int size = deq.size();
            if (check && start % 2 == 0) {
                bw.write("[");
                for (int j = 0; j < size; j++) {
                    if (j < size-1)
                        bw.write(deq.pollFirst() + ",");
                    else
                        bw.write(deq.pollFirst() + "");
                }
                bw.write("]\n");
            } else if(check && start % 2 == 1){
                bw.write("[");
                for (int j = 0; j < size; j++) {
                    if (j < size-1)
                        bw.write(deq.pollLast() + ",");
                    else
                        bw.write(deq.pollLast() + "");
                }
                bw.write("]\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
