import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1707 {
    static int[] numbering;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            numbering = new int[v + 1];

            graph = new ArrayList<>();
            for (int i = 0; i <= v; i++) {
                graph.add(new ArrayList());
            }

            // 그래프 구성
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            boolean flag = true;
            // 연결그래프가 아닐 수도 있으므로 탐색하지 않은 모든 노드 탐색
            for (int i = 1; i <= v; i++) {
                if (numbering[i] == 0) {
                    // 인접한 한 덩어리의 노드들로 이분그래프를 만들지 못한다면
                    // 다른 노드들을 탐색할 필요가 없다.(어차피 만들지 못하므로)
                    if (!bfs(i, e)) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }
        bw.close();
        br.close();
    }

    static boolean bfs(int start, int e) {
        Queue<Integer> q = new LinkedList<>();

        q.offer(start);
        numbering[start] = 1;

        while (!q.isEmpty() && e-- >= 0) {
            int cur = q.poll();

            for (int next : graph.get(cur)) {
                // 방문하지 않았던 노드라면 현재노드의 번호와 비교하여 다음 노드의 번호를 매김
                if (numbering[next] == 0) {
                    q.offer(next);
                    numbering[next] = (numbering[cur] == 1) ? 2 : 1;
                }

                // 다음노드와 현재노드의 번호가 같다면 이분그래프를 만들 수 없다
                if (numbering[cur] == numbering[next]) {
                    return false;
                }
            }
        }
        return true;
    }
}
