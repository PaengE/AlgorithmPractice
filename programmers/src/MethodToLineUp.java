import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MethodToLineUp {
    public int[] solution(int n, long k) {
        ArrayList<Integer> arr = new ArrayList<>();
        int[] ans = new int[n];
        long fn = 1;
        for (int i = 1; i <= n; i++) {
            arr.add(i);
            fn *= i;
        }
        k--;

        int idx = 0;
        while (n > 0) {
            fn /= n;
            ans[idx++] = arr.get((int) (k / fn));
            arr.remove((int) (k / fn));
            k %= fn;
            n--;
        }

        return ans;
    }

    // 순열을 이용한 풀이는 효율성 테스트에서 시간초과
//    public void makePermutation(ArrayList<Integer> arr, ArrayList<Integer> res, int n, int r, long idx) {
//        if (r == 0) {
//            cnt += 1;
//            if (idx == cnt) {
//                ans = res.stream().mapToInt(i -> i).toArray();
//            }
//            return;
//        }
//
//        for (int i = 0; i < n; i++) {
//            res.add(arr.remove(i));
//            makePermutation(arr, res, n - 1, r - 1, idx);
//            arr.add(i, res.remove(res.size() - 1));
//        }
//    }

    @Test
    public void test() {
        Assertions.assertArrayEquals(new int[]{3, 1, 2}, solution(3, 5));
    }
}
