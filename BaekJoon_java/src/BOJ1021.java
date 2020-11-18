import java.io.*;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * no.1021 : 회전하는 큐
 * hint : Deque 활용      ## 빼는건 맨 앞에서밖에 못뺌 !!
 *        왼쪽으로 shift할지 오른쪽으로 shift 할지를 결정
 */

public class BOJ1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]);
        int m = Integer.parseInt(s1[1]);

        String[] s2 = br.readLine().split(" ");

        Deque<Integer> deq = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            deq.offer(i);
        }
        
        int count = 0;
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(s2[i]);

            int idx = 1;
            // 뽑으려는 수가 현재 어디 위치에 있는지 찾음
            Iterator it = deq.iterator();
            while (it.hasNext()) {
                int t = (int)it.next();
                if(target == t){
                    break;
                }
                idx++;
            }

            // 위치가 덱 사이즈 / 2 + 1 보다 작거나 같으면 왼쪽 shift가 최소
            if (idx <= deq.size() / 2 + 1){
                for (int j = 1; j < idx; j++) {
                    deq.offerLast(deq.pollFirst());
                    count++;
                }
            }
            // 위치가 덱 사이즈 / 2 + 1 보다 크면 오른쪽 shift가 최소
            else {
                for (int j = 0; j < deq.size() - idx + 1; j++) {
                    deq.offerFirst(deq.pollLast());
                    count++;
                }
            }
            if(target == deq.peekFirst()){
                deq.pollFirst();
            }
        }
        bw.write(count + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
