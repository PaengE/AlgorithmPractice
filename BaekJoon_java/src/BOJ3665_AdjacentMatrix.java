import java.io.*;
import java.util.*;

/**
 *  No.3665: 최종 순위
 *  URL: https://www.acmicpc.net/problem/3665
 *  Description: 간선을 직접 정의한 다음 위상정렬을 하는 문제
 *  Hint: inDegree 가 저장된 2차원 배열을 Sort ( Adjacent Matrix 를 활용)
 *        -> inDegree 가 순위 정보를 나타내기 때문에 무조건 1씩 증가하는 형태를 보이므로
 *        -> 1씩 증가하는 형태가 아닌 경우, 순위 정보가 잘못 된 것 - IMPOSSIBLE
 */

public class BOJ3665_AdjacentMatrix {
    static final int TEAM = 0;
    static final int DEGREE = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // inDegree, map 생성
            int[][] degreeMap = new int[n + 1][2];
            boolean[][] map = new boolean[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                int teamNum = arr[i];

                degreeMap[teamNum][TEAM] = teamNum;
                degreeMap[teamNum][DEGREE] = i - 1;

                for (int j = 1; j < i; j++) {
                    map[teamNum][arr[j]] = true;
                }
            }

            // 순위 바꾸기
            int m = Integer.parseInt(br.readLine());
            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int team1 = Integer.parseInt(st.nextToken());
                int team2 = Integer.parseInt(st.nextToken());

                if (map[team1][team2]) {
                    degreeMap[team1][DEGREE] += -1;
                    degreeMap[team2][DEGREE] += 1;
                } else {
                    degreeMap[team1][DEGREE] += 1;
                    degreeMap[team2][DEGREE] += -1;
                }
                map[team1][team2] = !map[team1][team2];
                map[team2][team1] = !map[team2][team1];
            }

            // inDegree 값으로 sort
            Arrays.sort(degreeMap, (o1, o2) -> {
                return Integer.compare(o1[1], o2[1]);
            });

            StringBuilder tsb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (degreeMap[i][DEGREE] == i - 1) {
                    tsb.append(degreeMap[i][TEAM]).append(" ");
                } else {
                    tsb.replace(0, tsb.length(), "IMPOSSIBLE");
                    break;
                }
            }
            sb.append(tsb.toString()).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
