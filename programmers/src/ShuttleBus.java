import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class ShuttleBus {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> crew = new PriorityQueue<>();
        for (String str : timetable) {
            String[] s = str.split(":");
            int time = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
            crew.add(time);
        }

        int bustime = 9 * 60;   // 버스 출발 시간
        int lastBusTime = 0;    // 마지막 버스 출발 시간

        while (n-- > 0) {
            int capacity = m;   // 한 번에 태울수 있는 사람 수
            int lastTime = 0;   // 마지막 사람이 탄 시간

            while (!crew.isEmpty()) {
                // 출발 시간 이전 줄을 섰고, 태울 수 있으면 태움
                if (crew.peek() <= bustime && capacity > 0) {
                    capacity--;
                    lastTime = crew.poll();
                } else {
                    break;
                }
            }
            // 마지막 버스가 아닐 때
            if (n > 0) {
                // 기다리는 크루가 없으면 마지막 버스의 도착시간에 줄을 섬
                if (crew.isEmpty()) {
                    lastBusTime = bustime + (n + 1) * t;
                    break;
                }
                bustime += t;   // 다음 버스 출발시간 설정
            } else {    // 마지막 버스일 때
                if (capacity > 0) { // 자리가 남았으면 마지막 버스 도착시간에 줄을 섬
                    lastBusTime = bustime;
                } else {    // 자리가 없으면 마지막 사람이 탑승한 시간보다 1분 먼저 줄을 섬
                    lastBusTime = lastTime - 1;
                }
            }
        }

        String answer = String.format("%02d", lastBusTime / 60) + ":" + String.format("%02d", lastBusTime % 60);

        return answer;
    }

    @Test
    public void test() {
        Assertions.assertEquals("09:00", solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        Assertions.assertEquals("09:09", solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
        Assertions.assertEquals("08:59", solution(2, 1, 2, new String[]{"09:00", "09:00", "09:00", "09:00"}));
        Assertions.assertEquals("00:00", solution(1, 1, 5, new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"}));
        Assertions.assertEquals("09:00", solution(1, 1, 1, new String[]{"23:59"}));
        Assertions.assertEquals("18:00", solution(10, 60, 45, new String[]{"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }
}
