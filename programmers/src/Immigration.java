import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Immigration {
    static long ans;
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long start = 1;
        long end = (long) times[times.length - 1] * n;
        ans = end;

        binarySearch(start, end, n, times);
        return ans;
    }

    private void binarySearch(long start, long end, int n, int[] times) {
        long mid = (start + end) / 2;
        if (start >= end) {
            ans = Math.min(ans, mid);
            return;
        }

        long countedN = countFinishedPeople(mid, times);

        if (countedN < n) {
            binarySearch(mid + 1, end, n, times);
        } else {
            ans = Math.min(ans, mid);
            binarySearch(start, mid, n, times);
        }
    }

    private long countFinishedPeople(long time, int[] times) {
        long total = 0;
        for (long t : times) {
            total += time / t;
        }
        return total;
    }

    @Test
    public void test(){
        Assertions.assertEquals(28, solution(6, new int[]{7, 10}));
    }
}
