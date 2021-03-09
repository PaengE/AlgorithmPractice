import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.17471: 게리맨더링
 *  URL: https://www.acmicpc.net/problem/17471
 *  Hint: Combination + DFS
 */

public class BOJ17471 {
    static int n, ans = Integer.MAX_VALUE;
    static int[] citizen;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        citizen = new int[n];
        visited = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            citizen[i] = Integer.parseInt(st.nextToken());
        }

        // graph 구성
        graph = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(i);
            graph.add(new ArrayList<>());
            
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            for (int j = 0; j < t; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        for (int i = 1; i <= n / 2; i++) {
            combination(arr, res, n, i, 0);
        }

        if (ans == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(ans));
        }
        bw.close();
        br.close();
    }

    static void combination(ArrayList<Integer> arr, ArrayList<Integer> res, int n, int r, int idx) {
        if (r == 0) {
            int size = res.size();
            int[] areaA = new int[size];
            int[] areaB = new int[n - size];

            // areaA, areaB 초기화
            int j = 0, idxA = 0, idxB = 0;
            for (int i = 0; i < n; i++) {
                if (j == res.size()) {
                    areaB[idxB++] = i;
                } else if (res.get(j) == i) {
                    areaA[idxA++] = i;
                    j++;
                } else {
                    areaB[idxB++] = i;
                }
            }

            // areaA 연결되어 있는지 check
            if (!check(areaA, size)) {
                return;
            }
            // areaB 연결되어 있는지 check
            if (!check(areaB, n - size)) {
                return;
            }

            int citizenA = countCitizen(areaA);
            int citizenB = countCitizen(areaB);
            ans = Math.min(ans, Math.abs(citizenA - citizenB));
            return;
        }

        for (int i = idx; i < n; i++) {
            res.add(arr.get(i));
            combination(arr, res, n, r - 1, i + 1);
            res.remove(res.size() - 1);
        }
    }

    // 주어진 area 가 연결되어 있는지
    static boolean check(int[] area, int size) {
        Arrays.fill(visited, false);
        dfs(area[0], area);
        for (int i = 0; i < size; i++) {
            if (!visited[area[i]]) {
                return false;
            }
        }
        return true;
    }

    // 그 지역의 시민 수 리턴
    static int countCitizen(int[] area) {
        int sum = 0;
        for (int i = 0; i < area.length; i++) {
            sum += citizen[area[i]];
        }
        return sum;
    }

    // DFS 수행
    static void dfs(int start, int[] area) {
        visited[start] = true;
        for (int i = 0; i < graph.get(start).size(); i++) {
            int next = graph.get(start).get(i);

            if (!visited[next]) {
                for (int j = 0; j < area.length; j++) {
                    if (next == area[j]) {
                        dfs(next, area);
                    }
                }
            }
        }
    }
}
