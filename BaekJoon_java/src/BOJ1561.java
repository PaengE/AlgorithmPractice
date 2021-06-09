import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.1561: 놀이 공원
 *  Hint: 이분탐색
 */

public class BOJ1561 {
    static long n, ansTime = Long.MAX_VALUE;
    static int m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[m];
        int maxMinute = 0;
        for (int i = 0; i < m; i++) {
            int execTime = Integer.parseInt(st.nextToken());
            arr[i] = execTime;
            maxMinute = Math.max(maxMinute, execTime);
        }

        // n이 m보다 작으면 0초에 모든 사람이 놀이기구에 탈 수 있음
        if (n <= m) {
            bw.write(String.valueOf(n));
        } else {
            n -= m; // 0초에 타는 사람들을 제외한 나머지 사람 수
            binarySearch(1, n * maxMinute);
            bw.write(String.valueOf(lastNumber(ansTime)));
        }

        bw.close();
        br.close();
    }

    // 모든 사람이 놀이기구를 타는 최소시간을 구하는 이분탐색
    static void binarySearch(long left, long right) {
        while (left <= right) {
            long mid = (left + right) / 2;

            if (verifyingTime(mid)) {
                right = mid - 1;
                ansTime = Math.min(ansTime, mid);
            } else {
                left = mid + 1;
            }
        }
    }

    // time 안에 모든 사람이 놀이기구를 탈 수 있는지를 판단
    static boolean verifyingTime(long time) {
        long cnt = 0;
        for (int i = 0; i < m; i++) {
            cnt += (time / arr[i]);
        }

        return cnt >= n;
    }

    // 주어진 time에 마지막 사람이 타는 놀이기구의 번호를 찾음
    static int lastNumber(long time) {
        long num = n;
        // time - 1까지 놀이기구를 타지 않은 사람 수를 계산
        for (int i = 0; i < m; i++) {
            num -= ((time - 1) / arr[i]);
        }

        // 번호가 작은 빈 놀이기구부터 사람을 한명씩 태움
        for (int i = 0; i < m; i++) {
            if (time % arr[i] == 0) {
                num--;
            }

            if (num == 0) {
                return (i + 1);
            }
        }
        return -1;
    }
}
