import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.16957: 체스판 위의 공
 *  Hint: UnionFind + DFS
 */

public class BOJ16957 {
    static int r, c;
    static int[] parent;
    static int[][] arr;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[r][c];
        parent = new int[r * c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                parent[i * c + j] = i * c + j;
            }
        }

        bw.write(solve());
        bw.close();
        br.close();
    }

    static String solve() {
        StringBuilder sb = new StringBuilder();

        // 방문하지 않은 위치를 기준으로 DFS 수행
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (parent[i * c + j] == i * c + j) {
                    dfs(i, j);
                }
            }
        }

        // parent 를 참조하여 정답 배열 ans 구성
        int maxSize = r * c;
        int[] ans = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            ans[find(i)]++;
        }

        // 정답 String 구성
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(ans[i * c + j] + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    static void dfs(int cx, int cy) {
        int minVal = arr[cx][cy];
        int minX = 0, minY = 0;

        // 8방향 검사
        for (int i = 0; i < 8; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            if (isInRange(nx, ny) && minVal > arr[nx][ny]) {
                minVal = arr[nx][ny];
                minX = nx;
                minY = ny;
            }
        }

        // 이동할 수 있으면 현재 위치의 parent 를 갱신 후 DFS
        if (minVal < arr[cx][cy]) {
            parent[cx * c + cy] = minX * c + minY;
            if (parent[minX * c + minY] == minX * c + minY) {
                dfs(minX, minY);
            }
        }
    }

    // x 의 parent 를 찾음
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    // 체스판의 범위 체크
    static boolean isInRange(int x, int y) {
        if (x >= 0 && x < r && y >= 0 && y < c) {
            return true;
        }
        return false;
    }
}

/**
 *  시간초과 코드 (DFS)
 */

//import java.io.*;
//import java.util.StringTokenizer;
//
//public class BOJ16957 {
//    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
//    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
//    static int[][] arr, ans;
//    static int r, c;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        r = Integer.parseInt(st.nextToken());
//        c = Integer.parseInt(st.nextToken());
//        arr = new int[r][c];
//        ans = new int[r][c];
//
//        for (int i = 0; i < r; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < c; j++) {
//                arr[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        for (int i = 0; i < r; i++) {
//            for (int j = 0; j < c; j++) {
//                dfs(i, j);
//            }
//        }
//
//        bw.write(printAnswer().toString());
//        bw.close();
//        br.close();
//    }
//
//    static void dfs(int cx, int cy) {
//        int nx = Integer.MAX_VALUE, ny = Integer.MAX_VALUE;
//        int minVal = Integer.MAX_VALUE;
//
//        for (int i = 0; i < 8; i++) {
//            int tx = cx + dx[i];
//            int ty = cy + dy[i];
//
//            if (isInRange(tx, ty) && isLowerValueLocation(cx, cy, tx, ty, minVal)) {
//                minVal = arr[tx][ty];
//                nx = tx;
//                ny = ty;
//            }
//        }
//
//        if (nx == Integer.MAX_VALUE) {
//            ans[cx][cy]++;
//        } else {
//            dfs(nx, ny);
//        }
//    }
//
//    static boolean isLowerValueLocation(int cx, int cy, int tx, int ty, int minVal) {
//        if (arr[tx][ty] < arr[cx][cy] && arr[tx][ty] < minVal) {
//            return true;
//        }
//        return false;
//    }
//
//    static boolean isInRange(int x, int y) {
//        if (x >= 0 && x < r && y >= 0 && y < c) {
//            return true;
//        }
//        return false;
//    }
//
//    static StringBuilder printAnswer() {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < r; i++) {
//            for (int j = 0; j < c; j++) {
//                sb.append(ans[i][j] + " ");
//            }
//            sb.append("\n");
//        }
//        return sb;
//    }
//
//    static class Point{
//        int x, y;
//
//        Point(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//}
