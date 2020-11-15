import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * No.2887: 행성 터널
 * Description: 문제의 특성을 활용하여 고려할 간선의 개수를 줄임으로써 푸는 문제
 * URL: https://www.acmicpc.net/problem/2887
 * Hint: 점들끼리의 간선 가중치가 min(\x1 - x2\, \y1 - y2\, \z1 - z2\) 이므로 
 *       각 점들을 x, y, z 오름차순으로 정렬하여 인접한 점들끼리 간선을 만들고 크루스칼을 적용
 */

public class BOJ2887 {
    static int n;
    static int[] p;
    static Planet[] planets;
    static PriorityQueue<int[]> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        planets = new Planet[n];
        p = new int[n];

        // distance 기준으로 작은 것부터 poll
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets[i] = new Planet(i, x, y, z);
            p[i] = i;
        }

        createEdge();

        int ans = kruskal(n);

        bw.write(String.valueOf(ans));
        bw.flush();
        br.close();
        bw.close();
    }

    // 이웃한 점끼리 간선을 만드는 메소드
    static void createEdge() {
        // x 좌표로 정렬 후 인접한 점들끼리 간선을 만들어 PriorityQueue 에 삽입
        Arrays.sort(planets, Comparator.comparingInt(o -> o.x));
        for (int i = 0; i < n - 1; i++) {
            int distance = Math.abs(planets[i].x - planets[i + 1].x);
            pq.offer(new int[]{planets[i].idx, planets[i + 1].idx, distance});
        }

        // y 좌표로 정렬 후 인접한 점들끼리 간선을 만들어 PriorityQueue 에 삽입
        Arrays.sort(planets, Comparator.comparingInt(o -> o.y));
        for (int i = 0; i < n - 1; i++) {
            int distance = Math.abs(planets[i].y - planets[i + 1].y);
            pq.offer(new int[]{planets[i].idx, planets[i + 1].idx, distance});
        }

        // z 좌표로 정렬 후 인접한 점들끼리 간선을 만들어 PriorityQueue 에 삽입
        Arrays.sort(planets, Comparator.comparingInt(o -> o.z));
        for (int i = 0; i < n - 1; i++) {
            int distance = Math.abs(planets[i].z - planets[i + 1].z);
            pq.offer(new int[]{planets[i].idx, planets[i + 1].idx, distance});
        }
    }

    // 크루스칼 알고리즘
    static int kruskal(int nodeCount) {
        int res = 0, cnt = 0;

        while (!pq.isEmpty()) {
            // 우선순위 큐를 이용하여 최소 가중치 간선을 꺼냄
            int[] edge = pq.poll();


            if (findSet(p, edge[0]) != findSet(p, edge[1])) {
                // 연결된 weight 를 총 비용에 더함
                res += edge[2];

                // 모두 연결됐으면 break
                if (++cnt == nodeCount) {
                    break;
                }

                // v1 set 과 v2 set 을 합침
                union(p, edge[0], edge[1]);
            }

        }
        return res;
    }

    //x가 속한 집합의 부모를 찾는다.
    public static int findSet(int[] p, int x) {
        if (p[x] == x)
            return x;
        else
            return p[x] = findSet(p, p[x]);
    }
    //x와 y를 같은 집합으로 합친다.(노드 번호가 작은 것이 위로 가게끔)
    public static void union(int[] p, int x, int y) {
        if (x < y){
            p[findSet(p, y)] = findSet(p, x);
        }
        else{
            p[findSet(p, x)] = findSet(p, y);
        }

    }

    public static class Planet {
        int idx;
        int x, y, z;
        public Planet(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
