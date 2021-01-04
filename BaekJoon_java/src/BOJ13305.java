import java.io.*;
import java.util.StringTokenizer;

/**
 *  no.13305: 주유소
 *  url: https://www.acmicpc.net/problem/13305
 *  hint: long 자료형...
 *      1. 기름값이 현재도시보다 더 작은 도시를 찾음.
 *      2. 그 도시까지의 비용(기름값 * 거리)을 계산 후, 이동.
 *      3. 마지막 도시에 도착할 때까지 1번과 2번을 반복
 */

public class BOJ13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        long[] dist = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n; i++) {
            dist[i] = Long.parseLong(st.nextToken());
        }

        long[] city = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            city[i] = Long.parseLong(st.nextToken());
        }

        Long answer = 0L;
        int cur = 0;
        for (int end = 1; end < n; end++) {
            answer += city[cur] * dist[end];
            if (city[cur] > city[end]) {
                cur = end;
            }
        }

        bw.write(String.valueOf(answer));
        bw.close();
        br.close();
    }
}
