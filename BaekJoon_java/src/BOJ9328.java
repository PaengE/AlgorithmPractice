import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  NO.9328: 열쇠
 *  URL: https://www.acmicpc.net/problem/9328
 *  Hint: BFS
 */

public class BOJ9328 {
    static int n, m, ans;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;    // 지도를 저장
    static boolean[] myKeys;    //  현재 가지고 있는 키들을 저장
    static boolean[][] visited;
    static ArrayList<Space> entrance;   // 입장 가능한 입구를 저장함
    static ArrayList<Space>[] doors;    // 키가 없어서 들어가지 못하는 칸을 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            init();

            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = s.charAt(j);
                }
            }

            // 가지고 있는 키를 저장
            char[] c = br.readLine().toCharArray();
            if (c[0] != '0') {
                for (int i = 0; i < c.length; i++) {
                    myKeys[c[i] - 'a'] = true;
                }
            }

            setEntrance();
            bfs();
            bw.write(ans + "\n");
        }
        bw.close();
        br.close();
    }

    // 지도의 가장자리를 검사하여 입구를 찾음
    static void setEntrance() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 지도의 외곽을 검사
                if ((i == 0 || i == n - 1 || j == 0 || j == m - 1)) {
                    if (map[i][j] == '.') {
                        entrance.add(new Space(i, j));
                    } else if (map[i][j] >= 'a' && map[i][j] <= 'z') {
                        myKeys[map[i][j] - 'a'] = true;
                        entrance.add(new Space(i, j));
                        while (!doors[map[i][j] - 'a'].isEmpty()) {
                            entrance.add(doors[map[i][j] - 'a'].remove(0));
                        }
                    } else if (map[i][j] >= 'A' && map[i][j] <= 'Z') {
                        if (myKeys[map[i][j] - 'A']) {
                            entrance.add(new Space(i, j));
                        } else {
                            doors[map[i][j] - 'A'].add(new Space(i, j));
                        }
                    } else if (map[i][j] == '$') {
                        ans++;
                        entrance.add(new Space(i, j));
                    }
                }
            }
        }
    }

    static void bfs() {
        Queue<Space> q = new LinkedList<>();

        // 들어갈 수 있는 입구를 모두 큐에 넣음
        while (!entrance.isEmpty()) {
            Space t = entrance.remove(0);
            q.offer(t);
            visited[t.x][t.y] = true;
        }

        // BFS 수행
        while (!q.isEmpty()) {
            Space cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!inRange(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                char c = map[nx][ny];
                if (c == '.') { // 빈 공간인 칸
                    q.offer(new Space(nx, ny));
                    visited[nx][ny] = true;
                } else if (c >= 'a' && c <= 'z') {  // 열쇠가 있는 칸
                    // 내 열쇠 묶음에 추가
                    myKeys[c - 'a'] = true;
                    q.offer(new Space(nx, ny));
                    visited[nx][ny] = true;

                    // 해당 열쇠가 없어서 못갔던 곳이 있으면 큐에 추가
                    while (!doors[c - 'a'].isEmpty()) {
                        q.offer(doors[c - 'a'].remove(0));
                    }
                } else if (c >= 'A' && c <= 'Z') {  // 문이 있는 칸
                    if (myKeys[c - 'A']) {  // 열쇠가 있으면 이동
                        visited[nx][ny] = true;
                        q.offer(new Space(nx, ny));
                    } else {    // 열쇠가 없으면 해당 열쇠가 없어서 못갔던 문임을 저장
                        doors[c - 'A'].add(new Space(nx, ny));
                    }
                } else if (c == '$') {  // 훔쳐야할 문서가 있는 칸
                    ans++;
                    visited[nx][ny] = true;
                    q.offer(new Space(nx, ny));
                }
            }
        }
    }

    // 지도의 범위를 벗어나지 않는지 검사
    static boolean inRange(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }

    static class Space {
        int x, y;

        Space(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 각 테스트 케이스마다 변수 초기화
    static void init() {
        ans = 0;
        map = new char[n][m];
        visited = new boolean[n][m];
        entrance = new ArrayList<>();
        doors = new ArrayList[26];
        myKeys = new boolean[26];
        for (int i = 0; i < 26; i++) {
            doors[i] = new ArrayList<>();
        }
    }
}
