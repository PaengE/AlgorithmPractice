import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17135 {
    static int n, m, d, ans;
    static int[][] arr, copy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        copy = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<Integer> archer = new ArrayList<>();
        combination(archer, m, 3, 0);

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();

    }

    // 궁수가 서있을 column을 정함 (조합) -> 궁수는 항상 n번째 행에 존재함 (n, @)
    static void combination(ArrayList<Integer> archer, int n, int r, int idx) {
        if (r == 0) {
            initCopy(); // 새로 copy 배열을 초기화
            attack(archer);  // 만든 궁수 위치로 공격을 수행
            return;
        }

        for (int i = idx; i < n; i++) {
            archer.add(i);
            combination(archer, n, r - 1, i + 1);
            archer.remove(archer.size() - 1);
        }
    }

    //
    static void attack(ArrayList<Integer> archer) {
        int res = 0;
        // 게임은 n번 할 수있음
        for (int i = 1; i <= n; i++) {
            boolean[][] check = new boolean[n][m];

            for (int pos : archer) {
                int minD = Integer.MAX_VALUE;   // 현재 궁수 위치에서 가장 가까운 적과의 거리
                int minR = Integer.MAX_VALUE;   // 현재 궁수 위치에서 가장 가까운 적의 Row
                int minC = Integer.MAX_VALUE;   // 현재 궁수 위치에서 가장 가까운 적의 Column

                // 현재 궁수 위치에서 모든 점을 탐색하여, 적이 있으면 거리를 계산
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < m; k++) {
                        if (copy[j][k] == 1 && getDistance(j, k, n, pos) <= minD) {
                            // 최소거리가 갱신되면 최소거리, Row, Column 을 갱신
                            if (minD > getDistance(j, k, n, pos)) {
                                minD = getDistance(j, k, n, pos);
                                minR = j;
                                minC = k;
                            } else {    // 최소거리가 같으면 Row, Column 만 갱신(왼쪽이 우선)
                                if (minC > k) {
                                    minR = j;
                                    minC = k;
                                }
                            }
                        }
                    }
                }

                // 최소거리가 궁수의 사정거리 안이면, 적의 위치를 기록
                if (minD <= d) {
                    check[minR][minC] = true;
                }
            }

            // 기록된 적들을 한꺼번에 없애고 그 수를 counting
            // 바로바로 죽이지 않는 이유는 하나의 적을 여러 궁수가 공격할 수 있기 때문
            res += kill(check);
            afterAttack(i); // 적의 위치를 한칸씩 밑으로 내림
        }
        ans = Math.max(ans, res);
    }

    // boolean 배열을 검사하여 true 이면 카운팅을 증가시키고 copy 배열에서 적을 없앰
    static int kill(boolean[][] check) {
        int killCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (check[i][j]) {
                    killCnt++;
                    copy[i][j] = 0;
                }
            }
        }
        return killCnt;
    }

    // 적군 배열을 한칸씩 아래로 이동
    static void afterAttack(int lv) {
        if (lv == n) {
            return;
        }

        for (int i = n - 1; i >= lv; i--) {
            copy[i] = copy[i - 1].clone();
        }
        for (int i = 0; i < lv; i++) {
            Arrays.fill(copy[i], 0);
        }
    }

    static void initCopy() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = arr[i][j];
            }
        }
    }

    static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
