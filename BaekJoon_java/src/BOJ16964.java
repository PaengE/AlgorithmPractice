import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *  No.16964: DFS 스페셜 저지
 *  URL: https://www.acmicpc.net/problem/16964
 *  Hint: DFS 순서가 맞는지를 판단
 */

public class BOJ16964 {
    static int n, idx = 1;
    static boolean[] visited;
    static boolean correct = true;
    static ArrayList<Integer> arr;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 그래프 구성
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        // 방문 순서가 1로 시작하지 않으면 잘못된 순서
        if (arr.get(0) != 1) {
            bw.write("0");
        } else {
            visited[1] = true;
            dfs(1);
            if (correct) {
                bw.write("1");
            } else {
                bw.write("0");
            }
        }

        bw.close();
        br.close();
    }
    
    static void dfs(int cur) {
        // 잘못된 방문 순서를 알았으면 더이상 진행 x
        if (!correct) {
            return;
        }

        // 현재 노드와 연결된 방문하지 않은 모든 노드들을 set에 추가
        HashSet<Integer> set = new HashSet<>();
        for (int next : list[cur]) {
            if (!visited[next]) {
                visited[next] = true;
                set.add(next);
            }
        }

        // dfs 수행
        int size = set.size();
        for (int i = 0; i < size; i++) {
            if (set.remove(arr.get(idx))) {
                dfs(arr.get(idx++));
            } else {
                if (!set.isEmpty()) {
                    correct = false;
                    return;
                }
            }
        }
    }
}