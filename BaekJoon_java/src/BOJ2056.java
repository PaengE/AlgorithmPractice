import java.io.*;
import java.util.*;

public class BOJ2056 {
    static int n;
    static int[] inDegree, timeCost, finishTime;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        inDegree = new int[n + 1];
        list = new ArrayList[n + 1];
        timeCost = new int[n + 1];
        finishTime = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            timeCost[i] = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            for (int j = 0; j < count; j++) {
                int num = Integer.parseInt(st.nextToken());
                inDegree[i]++;
                list[num].add(i);
            }
        }

        int answer = topologicalSort();

        bw.write(String.valueOf(answer));
        bw.close();
        br.close();
    }

    // 위상 정렬
    static int topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        // 차수가 0인 모든 작업을 큐에 넣음
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                finishTime[i] = timeCost[i];
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            // 각 작업이 끝나는 시간을 계산하며
            // 현재 작업이 완료 되었으면, 후행 작업들의 차수를 하나씩 내림
            // 후행 작업의 차수가 0이면(실행할 수 있으면)
            for (int next : list[cur]) {
                if (finishTime[next] < finishTime[cur] + timeCost[next]) {
                    finishTime[next] = finishTime[cur] + timeCost[next];
                }
                if (--inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        return Arrays.stream(finishTime).max().getAsInt();
    }
}
