import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExteriorWallInspection {
    private int n, answer;
    private int[] weak, dist, spreadWeak;
    private boolean finish;
    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        makeSpreadWeak();
        finish = false;

        answer = Integer.MAX_VALUE;

        // i자리 순열 만들기
        for (int i = 1; i <= dist.length; i++) {
            permutation(0, i, new boolean[dist.length], new int[i]);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    // 친구로 순열을 만듬
    private void permutation(int depth, int num, boolean[] visited, int[] res) {
        // 종료 flag
        if (finish) {
            return;
        }

        if (depth == num) {
            checkIfCanCover(res);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                res[depth] = dist[i];
                visited[i] = true;
                permutation(depth + 1, num, visited, res);
                visited[i] = false;
            }
        }
    }

    private void checkIfCanCover(int[] res) {
        // 시작점 i 기준으로 한바퀴를 돔
        for (int i = 0; i < weak.length; i++) {
            int start = i;
            boolean flag = true;

            // 친구 수 만큼
            for (int idx = 0; idx < res.length; idx++) {
                // i 위치에서 weak point 개수만큼
                for (int j = i; j < i + weak.length; j++) {
                    // 두 점 사이의 거리가 검사가능한 거리보다 크면, 커버할 수 없음 -> 다른 친구를 검사함.
                    // 현재 지점을 시작점으로 지정(이전 지점은 검사가 완료됐으므로)
                    if (spreadWeak[j] - spreadWeak[start] > res[idx]) {
                        start = j;
                        idx++;

                        // 현재 지점을 커버할 수 없는데 다음친구가 없다면, 남은 지점들을 검사할 필요가 없음.
                        if (idx == res.length) {
                            flag = false;
                            break;
                        }
                    }

                }

                // 주어진 사람으로 전체 weak point를 커버할 수 있다면
                // 더이상 검사를 진행할 필요가 없다.(작은 크기의 순열부터 만들었으므로 문제에서 요구한 최솟값은 이미 구해짐)
                //
                if (flag) {
                    answer = idx + 1;
                    finish = true;
                    return;
                }
            }
        }
    }

    // weak point를 일자로 펼치는 과정
    private void makeSpreadWeak() {
        int size = weak.length;
        spreadWeak = new int[size * 2 - 1];

        for (int i = 0; i < size; i++) {
            spreadWeak[i] = weak[i];
        }

        for (int i = 0; i < size - 1; i++) {
            spreadWeak[i + size] = weak[i] + n;
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals(2, solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
        Assertions.assertEquals(1, solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));
        Assertions.assertEquals(2, solution(200, new int[]{0, 100}, new int[]{1, 1}));
        Assertions.assertEquals(3, solution(200, new int[]{0, 10, 50, 80, 120, 160}, new int[]{1, 10, 5, 40, 30}));
        Assertions.assertEquals(1, solution(12, new int[]{0, 10}, new int[]{1, 2}));
        Assertions.assertEquals(1, solution(12, new int[]{4}, new int[]{1, 2}));
        Assertions.assertEquals(2, solution(30, new int[]{0, 3, 11, 21}, new int[]{10, 4}));
        Assertions.assertEquals(-1, solution(200, new int[]{1, 3, 5, 7, 9, 11}, new int[]{1, 1, 1, 1, 1}));
        Assertions.assertEquals(1, solution(19, new int[]{0, 10}, new int[]{9}));
    }
}
