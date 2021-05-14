import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Monthly6 {
    public long[] solution(long[] numbers) {
        long[] ans = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long a = numbers[i];
            StringBuilder binaryA = new StringBuilder(Long.toBinaryString(a));

            int aIdx = binaryA.lastIndexOf("0");
            if (aIdx == binaryA.length() - 1) {
                binaryA.replace(aIdx, aIdx + 1, "1");
            } else {
                if (aIdx == -1) {
                    binaryA.delete(0, 1).insert(0, "10");
                } else {
                    binaryA.delete(aIdx, aIdx + 2).insert(aIdx, "10");
                }
            }
            ans[i] = Long.parseLong(binaryA.toString(), 2);
        }

        return ans;
    }

    @Test
    public void test() {
        Assertions.assertArrayEquals(new long[]{3, 11}, solution(new long[]{2, 7}));
    }
}
