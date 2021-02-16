import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.PriorityQueue;

public class OvertimeScore {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < works.length; i++) {
            pq.offer(works[i]);
        }

        while (n-- > 0) {
            int t = pq.poll();
            if (t == 0) {
                return 0;
            }
            pq.offer(--t);
        }

        // 제곱 합 계산
        long ans = pq.stream().mapToLong(i -> (long) Math.pow(i, 2)).sum();
        return ans;
    }

    @Test
    public void test() {
        Assertions.assertEquals(12, solution(4, new int[]{4, 3, 3}));
        Assertions.assertEquals(6, solution(1, new int[]{2, 1, 2}));
        Assertions.assertEquals(0, solution(3, new int[]{1, 1}));
        Assertions.assertEquals(580, solution(99, new int[]{2, 15, 22, 55, 55}));
    }
}
