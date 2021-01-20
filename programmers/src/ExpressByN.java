import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpressByN {
    static int[] nth = new int[8];
    static int result;
    public int solution(int n, int number) {
        result = -1;
        String s = "";
        for (int i = 0; i < 8; i++) {
            s += n;
            nth[i] = Integer.parseInt(s);
        }

        dfs(0, 0, number);
        return result;
    }

    public void dfs(int count, int num, int number) {
        if (count > 8) {
            return;
        }

        if (num == number) {
            if (count < result || result == -1) {
                result = count;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            int nn = nth[i];
            int cntN = i + 1;

            dfs(count + cntN, num + nn, number);
            dfs(count + cntN, num - nn, number);
            dfs(count + cntN, num * nn, number);
            dfs(count + cntN, num / nn, number);
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals(4, solution(5, 12));
        Assertions.assertEquals(3, solution(2, 11));
        Assertions.assertEquals(-1, solution(5, 31168));
    }
}
