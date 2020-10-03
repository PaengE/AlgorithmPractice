import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * no.2110 : 공유기 설치
 * title : 이진 탐색을 응용하여 최솟값이나 최댓값을 찾는 문제 3
 * hint : 1. 이진 탐색으로 특정 간격 위치에 공유기를 설치.
 *        2. 공유기를 더 놓아야 한다면 간격을 줄이고,
 *        3. 공유기를 줄여야 한다면 간격을 늘린다.
 */

public class BOJ2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        long[] houses = new long[n];

        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        long left = 1;
        long right = houses[n - 1] - houses[0];
        long length = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            int cnt = 1;
            long prev = houses[0];

            for (long house : houses) {
                if (house - prev >= mid) {
                    cnt++;
                    prev = house;
                }
            }

            if (cnt >= c) {
                length = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        bw.write(length + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
