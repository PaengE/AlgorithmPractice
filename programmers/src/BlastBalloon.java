import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BlastBalloon {
    public int solution(int[] a) {
        if (a.length == 1) {
            return 1;
        }

        int left = a[0];    // a의 왼쪽 끝
        int right = a[a.length - 1];    // a의 오른쪽 끝
        int ans = 2;

        for (int i = 1; i < a.length - 1; i++) {
            // 왼쪽 끝+1의 요소가 left보다 작으면 남길 수 있음
            if (a[i] < left) {
                left = a[i];
                ans++;
            }

            // 오른쪽 끝-1의 요소가 right보다 작으면 남길 수 있음
            if (a[a.length - 1 - i] < right) {
                right = a[a.length - 1 - i];
                ans++;
            }

            // left == right 면 중복된 답이 들어갔으므로 ans--
            if (left == right) {
                ans--;
                break;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Assertions.assertEquals(3, solution(new int[]{9, -1, -5}));
        Assertions.assertEquals(3, solution(new int[]{-10, 10, -20, 30, 40, -60}));
        Assertions.assertEquals(7, solution(new int[]{-16, 27, 65, -100, 58, -92, -71, -68, -61, -33}));
    }
}
