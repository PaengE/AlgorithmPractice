import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChuseokTraffic {
    public int solution(String[] lines) {
        int[] startTime = new int[lines.length];
        int[] endTime = new int[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String[] s = lines[i].split(" ");
            String[] time = s[1].split(":");

            int processingTime = (int) (Double.parseDouble(s[2].substring(0, s[2].length() - 1)) * 1000);
            endTime[i] = (int) (((Double.parseDouble(time[0]) * 3600) + (Double.parseDouble(time[1]) * 60) + Double.parseDouble(time[2])) * 1000);
            startTime[i] = endTime[i] - processingTime + 1;
        }

        int length = lines.length;
        int max = 0;
        for (int i = 0; i < length; i++) {
            int startPlusOne = startTime[i] + 1000;
            int endPlusOne = endTime[i] + 1000;
            int count = 0;

            count = getCount(startTime, endTime, length, startPlusOne, 0, startTime[i], i);
            max = Math.max(count, max);

            count = getCount(startTime, endTime, length, endPlusOne, 0, endTime[i], i);
            max = Math.max(count, max);
        }

        return max;
    }

    private int getCount(int[] startTime, int[] endTime, int length, int startPlusOne, int count, int i2, int i) {
        for (int j = 0; j < length; j++) {
            if ((startTime[j] <= i2 && startPlusOne <= endTime[j])
                    || (i2 <= startTime[j] && startTime[j] < startPlusOne)
                    || (i2 <= endTime[j] && endTime[j] < startPlusOne)) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void test() {
        String[] lines1 = {"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"};
        String[] lines2 = {"2016-09-15 01:00:04.002 2.0s",
                "2016-09-15 01:00:07.000 2s"};
        String[] lines3 = {"2016-09-15 01:00:04.001 2.0s",
                "2016-09-15 01:00:07.000 2s"};
        String[] lines4 = {"2016-09-15 00:00:00.001 3s"};

        Assertions.assertEquals(7, solution(lines1));
        Assertions.assertEquals(2, solution(lines2));
        Assertions.assertEquals(1, solution(lines3));
        Assertions.assertEquals(1, solution(lines4));
    }
}
