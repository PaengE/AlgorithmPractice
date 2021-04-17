import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
class Monthly3 {
    static ArrayList<Integer>[] tree;
    static long[] w, dp;
    public long solution(int[] a, int[][] edges) {
        // 각 노드 가중치의 합이 0이 아니면 -1
        if (Arrays.stream(a).sum() != 0) {
            return -1;
        }
        w = new long[a.length];
        for (int i = 0; i < a.length; i++){
            w[i] = a[i];
        }

        tree = new ArrayList[w.length];
        for (int i = 0; i < w.length; i++) {
            tree[i] = new ArrayList<>();
        }

        // 양방향 간선 추가
        for (int i = 0; i < edges.length; i++) {
            tree[edges[i][0]].add(edges[i][1]);
            tree[edges[i][1]].add(edges[i][0]);
        }

        // 간선이 총 1개면(노드가 총 2개면)
        if (edges.length == 1) {
            return Math.abs(w[0]);
        }

        // 간선이 2개 이상인 노드를 루트노드로 함
        int root = 0;
        for(int i = 0; i < w.length; i++){
            if (tree[i].size() >= 2){
                root = i;
                break;
            }
        }

        long ans = buildTree(root, -1);
        return ans;
    }

    // 트리를 구성
    public long buildTree(int cur, int parent) {
        // 리프노드면
        if (tree[cur].size() == 1) {
            w[parent] += w[cur];
            return Math.abs(w[cur]);
        }

        long cnt = 0;
        // parent 가 아닌 간선을 child 로 등록
        for (int i = 0; i < tree[cur].size(); i++){ // for (int child : tree[cur]) 로 하면 런타임에러..
            int child = tree[cur].get(i);
            if(parent != child){
                cnt += buildTree(child, cur);
            }
        }

        // 현재 노드의 가중치가 0이 아니면 부모 노드에 가중치를 반영
        if (w[cur] != 0) {
            w[parent] += w[cur];
            cnt += Math.abs(w[cur]);
        }
        return cnt;
    }

    @Test
    public void test() {
        Assertions.assertEquals( 9, solution(new int[]{-5, 0, 2, 1, 2}, new int[][]{{0, 1}, {3, 4}, {2, 3}, {0, 3}}));
        Assertions.assertEquals(-1, solution(new int[]{0, 1, 0}, new int[][]{{0, 1}, {1, 2}}));
        Assertions.assertEquals(2, solution(new int[]{2, -2}, new int[][]{{0, 1}}));
    }
}