import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DiskController {
    public int solution(int[][] jobs) {
        // 요청시간 기준 오름차순
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        // 처리시간 기준 오름차순
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        int endTime = 0;    // 종료시간
        int jobsIdx = 0;    // 작업인덱스
        int countJobs = 0;  // 수행된 작업의 개수
        int ans = 0;

        while (countJobs < jobs.length) {
            // 시작시간이 종료시간보다 같거나 작으면 pq에 작업큐에 등록
            while (jobsIdx < jobs.length && jobs[jobsIdx][0] <= endTime) {
                pq.offer(jobs[jobsIdx++]);
            }

            // 작업큐가 비어있으면 종료시간을 다음 작업의 시작시간으로 지정(endTime 이후에 들어온 작업)
            if (pq.isEmpty()) {
                endTime = jobs[jobsIdx][0];
            }
            // 비어있지 않다면 하나씩 꺼내서 소요시간과 종료시간을 갱신
            else {
                int[] temp = pq.poll();
                ans += endTime + temp[1] - temp[0];
                endTime += temp[1];
                countJobs++;
            }
        }

        return ans / jobs.length;
    }

    @Test
    public void test() {
        Assertions.assertEquals(9, solution(new int[][]{{0, 3}, {1, 9}, {2, 6}}));
        Assertions.assertEquals(7, solution(new int[][]{{0, 3}, {1, 9}, {2, 6}, {30, 3}}));
        Assertions.assertEquals(3, solution(new int[][]{{0, 5}, {3, 2}, {7, 2}}));
        Assertions.assertEquals(6, solution(new int[][]{{0, 7}, {1, 1}, {5, 3}}));
        Assertions.assertEquals(550, solution(new int[][]{{100, 100}, {1000, 1000}}));
        Assertions.assertEquals(1, solution(new int[][]{{0, 1}}));
        Assertions.assertEquals(14, solution(new int[][]{{0, 10}, {2, 10}, {9, 10}, {15, 2}}));
        Assertions.assertEquals(25, solution(new int[][]{{0, 10}, {2, 12}, {9, 19}, {15, 17}}));
        Assertions.assertEquals(2, solution(new int[][]{{0, 1}, {0, 1}, {0, 1}}));
        Assertions.assertEquals(2, solution(new int[][]{{0, 1},{0, 1},{0, 1},{0, 1}}));
        Assertions.assertEquals(6, solution(new int[][]{{10, 10}, {30, 10}, {50, 2}, {51, 2}}));
        Assertions.assertEquals(550, solution(new int[][]{{100, 100}, {1000, 1000}}));
    }
}
