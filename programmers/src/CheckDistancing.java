import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class CheckDistancing {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static String[][] places;
	static char[][] map;
	static boolean flag;

	public int[] solution(String[][] places) {
		int[] ans = new int[5];
		map = new char[5][5];
		for (int i = 0; i < places.length; i++) {
			for (int j = 0; j < 5; j++) {
				map[j] = places[i][j].toCharArray();
			}

			flag = false;

			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					if (!flag && map[j][k] == 'P') {
						bfs(j, k);
					}
				}
			}

			ans[i] = flag ? 0 : 1;
		}
		return ans;
	}

	static void bfs(int sx, int sy) {
		Queue<Point> q = new ArrayDeque<>();

		for (int i = 0; i < 4; i++) {
			int nx = sx + dx[i];
			int ny = sy + dy[i];

			if (inRange(nx, ny)) {
				if (map[nx][ny] == 'P') {
					flag = true;
					return;
				}

				if (map[nx][ny] != 'X') {
					q.offer(new Point(nx, ny));
				}
			}
		}

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx == sx && ny == sy) {
					continue;
				}

				if (inRange(nx, ny)) {
					if (map[nx][ny] == 'P') {
						flag = true;
						break;
					}
				}
			}
		}
	}

	static boolean inRange(int x, int y) {
		if (x >= 0 && x < 5 && y >= 0 && y < 5) {
			return true;
		}
		return false;
	}

	static class Point{
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	@Test
	void test() {
		Assertions.assertArrayEquals(new int[]{1, 0, 1, 1, 1}
				, solution(new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}
						, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}
						, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}
						, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}
						, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}}));
	}
}