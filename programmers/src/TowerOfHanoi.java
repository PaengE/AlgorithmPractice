import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TowerOfHanoi {
    static int[][] ans;
    static int idx;
    public int[][] solution(int n) {
        ans = new int[(1 << n) - 1][2];
        idx = 0;
        hanoi(n, 1, 2, 3);

        return ans;
    }

    private void hanoi(int n, int from, int via, int to) {
        int[] move = {from, to};

        if (n == 1) {
            ans[idx++] = move.clone();
        } else {
            // 출발지에서 경유지로 n - 1개 원판 이동
            hanoi(n - 1, from, to, via);
            ans[idx++] = move.clone();
            // 경유지에서 도착지로 n - 1개 원판 이동
            hanoi(n - 1, via, from, to);
        }
    }

    @Test
    public void test() {
        Assertions.assertArrayEquals(new int[][]{{1, 2}, {1, 3}, {2, 3}}, solution(2));
    }
}
