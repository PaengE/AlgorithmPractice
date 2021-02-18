import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class N_Queens {
    static int n, count = 0;
    static int[] arr;
    public int solution(int n) {
        this.n = n;
        arr = new int[n];

        backtracking(0);

        return count;
    }

    static void backtracking(int lv){
        if (lv == n) {
            count++;
        } else {
            for (int i = 0; i < n; i++) {
                arr[lv] = i;
                if (isPossible(lv)) {
                    backtracking(lv + 1);
                }
            }
        }
    }

    static boolean isPossible(int lv) {
        for (int i = 0; i < lv; i++) {
            if (arr[i] == arr[lv] || Math.abs(arr[i] - arr[lv]) == Math.abs(lv - i)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        Assertions.assertEquals(2, solution(4));
    }
}
