import java.io.*;
import java.util.*;

public class BOJ2583 {
	static int n, m, k;
	static int[][] arr;
	static boolean[][] visited;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static ArrayList<Integer> areas = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());

			for (int j = sx; j < ex; j++) {
				for (int l = sy; l < ey; l++) {
					arr[j][l] = 1;
				}
			}
		}

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (canMove(i, j)) {
					cnt++;
					areas.add(bfs(i, j));
				}
			}
		}
		Collections.sort(areas);

		StringBuilder sb = new StringBuilder();
		sb.append(cnt + "\n");
		for (int i = 0; i < areas.size(); i++) {
			sb.append(areas.get(i) + " ");
		}

		bw.write(sb.toString());
		br.close();
		bw.close();
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
		return arr[x][y] == 0 && !visited[x][y];
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
