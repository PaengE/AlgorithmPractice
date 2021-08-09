import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1926 {
	static int n, m;
	static int[][] arr;
	static boolean[][] visited;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0, area = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (canMove(i, j)) {
					cnt++;
					area = Math.max(area, bfs(i, j));
				}
			}
		}

		bw.write(cnt + "\n" + area);
		bw.close();
		br.close();
	}

	static int bfs(int sx, int sy) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(sx, sy));
		visited[sx][sy] = true;

		int area = 1;
		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (inRange(nx, ny) && canMove(nx, ny)) {
					q.offer(new Point(nx, ny));
					visited[nx][ny] = true;
					area++;
				}
			}
		}

		return area;
	}

	static boolean canMove(int x, int y) {
		return arr[x][y] == 1 && !visited[x][y];
	}

	static boolean inRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

	static class Point{
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
