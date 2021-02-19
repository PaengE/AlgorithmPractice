import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BaseStationInstallation {
    public int solution(int n, int[] stations, int w) {
        int cur = 1;
        int range = 1 + w * 2;

        int answer = 0;

        for (int i = 0; i < stations.length; i++) {
            if (cur < stations[i] - w) {
                int apartRange = stations[i] - w - cur;
                answer += apartRange % range == 0 ? apartRange / range : apartRange / range + 1;
            }
            cur = stations[i] + w + 1;
        }

        int endTo = n - cur + 1;
        if (endTo > 0) {
            answer += endTo % range == 0 ? endTo / range : endTo / range + 1;
        }
        return answer;
    }

    @Test
    public void test() {
        Assertions.assertEquals(3, solution(11, new int[]{4,11}, 1));
        Assertions.assertEquals(3, solution(16, new int[]{9}, 2));
        Assertions.assertEquals(3, solution(16, new int[]{1}, 2));
    }
}
