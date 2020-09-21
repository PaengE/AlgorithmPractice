import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * no.1966 : 프린터 큐
 * hint : Iterator 사용
 */

class Docs{
    int docsNum;
    int priority;
    public Docs(int docsNum, int priority){
        this.docsNum = docsNum;
        this.priority = priority;
    }
}

public class BOJ1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String[] s1 = br.readLine().split(" ");
            int n = Integer.parseInt(s1[0]);
            int m = Integer.parseInt(s1[1]);
            Queue<Docs> q = new LinkedList<Docs>();

            String[] s2 = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                q.offer(new Docs(j, Integer.parseInt(s2[j])));
            }
            int count = 1;

            while(!q.isEmpty()){
                boolean check = true;
                Docs currentDocs = q.poll();

                // 큐에서 뺀 하나와 나머지 것들 중 우선순위를 검사
                Iterator it = q.iterator();
                while (it.hasNext()) {
                    Docs otherDocs = (Docs)it.next();
                    if (currentDocs.priority < otherDocs.priority) {
                        check = false;
                        break;
                    }
                }
                // 나머지 것들 중 우선순위가 높은게 있으면 맨 뒤로 보냄
                if (check == false) {
                    q.offer(currentDocs);
                }
                // 높은게 없는데 docsNum 이 m 이 아니면 count증가
                else {
                    if(currentDocs.docsNum == m){
                        bw.write(count + "\n");
                        break;
                    } else {
                        count++;
                    }
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
