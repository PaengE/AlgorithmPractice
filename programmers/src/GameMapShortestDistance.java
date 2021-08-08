import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class GameMapShortestDistance {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int row, col;

	public int solution(int[][] maps) {
		row = maps.length;
		col = maps[0].length;
		return bfs(maps);
	}

	static int bfs(int[][] maps) {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[row][col];

		visited[0][0] = true;
		q.offer(new Point(0, 0, 1));

		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (cur.x == row - 1 && cur.y == col - 1) {
				return cur.cnt;
			}

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (inRange(nx, ny) && maps[nx][ny] == 1 && !visited[nx][ny]) {
					q.offer(new Point(nx, ny, cur.cnt + 1));
					visited[nx][ny] = true;
				}
			}
		}

		return -1;
	}

	static boolean inRange(int x, int y) {
		return x >= 0 && x < row && y >= 0 && y < col;
	}

	static class Point{
		int x, y, cnt;

		Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	@Test
	void test() {
		Assertions.assertEquals(11, solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}}));
		Assertions.assertEquals(-1, solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}}));
	}
}
