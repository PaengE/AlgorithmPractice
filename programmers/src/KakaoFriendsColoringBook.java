import java.util.LinkedList;
import java.util.Queue;

public class KakaoFriendsColoringBook {
    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture =   { { 1, 0, 0, 1 }, { 1, 0, 0, 1 }, { 1, 0, 0, 1 }, { 1, 0, 0, 1 }, { 1, 0, 0, 1 }, { 1, 1, 1, 1 } };

        int[] answer = solution(m, n, picture);
        System.out.println(answer[0]);
        System.out.println(answer[1]);

    }

    static int[] solution(int m, int n, int[][] picture) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        boolean[][] visited = new boolean[m][n];
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();

        int maxSizeOfOneArea = 0;
        int numberOfArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (picture[i][j] != 0 && !visited[i][j]) {
                    long num = picture[i][j];
                    qx.offer(i);
                    qy.offer(j);
                    visited[i][j] = true;

                    int count = 1;
                    numberOfArea += 1;

                    while (!qx.isEmpty()) {
                        int cx = qx.poll();
                        int cy = qy.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = cx + dx[k];
                            int ny = cy + dy[k];

                            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                if (!visited[nx][ny] && picture[nx][ny] == num) {
                                    qx.offer(nx);
                                    qy.offer(ny);
                                    visited[nx][ny] = true;

                                    count += 1;
                                }
                            }
                        }
                    }
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, count);
                }
            }
        }
        return new int[]{numberOfArea, maxSizeOfOneArea};
    }
}
