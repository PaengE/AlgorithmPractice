import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class JumpAndTeleport {
    public static void main(String[] args) {
        int n = 1000000000;
        JumpAndTeleport jt = new JumpAndTeleport();
        System.out.println(jt.solution(n));
//        System.out.println(jt.mySolution(n));

    }

    // 주어진 수를 이진수로 나타냈을 때의 1의 개수
    public int solution(int n) {
        return Integer.bitCount(n);
    }

    // 시간초과..
    public int mySolution(int n) {
        Queue<Integer> q = new LinkedList<>();
        int[] cost = new int[n + 1];
        Arrays.fill(cost, -1);

        q.offer(0);
        cost[0] = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == n) {
                return cost[cur];
            }

            if (cur * 2 <= n && cost[cur * 2] == -1) {
                q.offer(cur * 2);
                cost[cur * 2] = cost[cur];
            }
            if (cur + 1 <= n && cost[cur + 1] == -1) {
                q.offer(cur + 1);
                cost[cur + 1] = cost[cur] + 1;
            }
        }

        return cost[n];
    }
}
