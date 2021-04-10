import java.io.*;
import java.util.*;

/**
 *  No.17143: 낚시왕
 *  URL: https://www.acmicpc.net/problem/17143
 *  Hint: 구현 시뮬레이션 문제
 */

public class BOJ17143 {
    static int r, c, m;
    static int[][] map; // 상어를 잡을 때 사용할 배열
    static int[][] tmp; // 상어를 움직인 후 임시로 저장할 배열
    static Shark[] sharks;  // 크기가 i인 상어들의 정보를 저장할 배열
    static Queue<Shark> sharksQ = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        tmp = new int[r][c];
        sharks = new Shark[10001];  // 상어의 최대 마리 수 만큼

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());   // 1:위, 2:아래, 3:오른, 4:왼
            int z = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(r - 1, c - 1, s, d, z);
            sharks[z] = shark;
            map[r - 1][c - 1] = z;
        }

        int ans = 0;
        for (int i = 0; i < c; i++) {
            ans += getShark(i);
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    // 상어를 잡는 일련의 과정 (잡은 상어의 크기를 리턴)
    static int getShark(int curC) {
        // 상어를 잡음(못 잡을 수도 있음)
        int sharkSize = 0;
        for (int i = 0; i < r; i++) {
            if (map[i][curC] != 0) {
                sharkSize = map[i][curC];
                map[i][curC] = 0;
                break;
            }
        }

        // 남은 상어들을 큐에 집어넣음
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != 0) {
                    sharksQ.offer(sharks[map[i][j]]);
                }
            }
        }

        // 상어들을 움직임
        initTmpArray();
        while(!sharksQ.isEmpty()) {
            move(sharksQ.poll());
        }

        // 움직임이 반영된 tmp 를 map 에 복사
        copyTmpToMap();
        return sharkSize;
    }

    // 상어 이동
    static void move(Shark cur) {
        if (cur.direction == 1) {   // 위
            cur.speed %= ((r - 1) * 2);
            cur.r -= cur.speed;

            if (cur.r < 0) {
                cur.r = Math.abs(cur.r);
                cur.direction = 2;
            }
            if (cur.r >= r) {
                cur.r = r - (cur.r - (r - 1) + 1);
                cur.direction = 1;
            }
        } else if (cur.direction == 2) {    // 아래
            cur.speed %= ((r - 1) * 2);
            cur.r += cur.speed;

            if (cur.r >= r) {
                cur.r = r - (cur.r - (r - 1) + 1);
                cur.direction = 1;
            }
            if (cur.r < 0) {
                cur.r = Math.abs(cur.r);
                cur.direction = 2;
            }
        } else if (cur.direction == 3) {    // 오른
            cur.speed %= ((c - 1) * 2);
            cur.c += cur.speed;

            if (cur.c >= c) {
                cur.c = c - (cur.c - (c - 1) + 1);
                cur.direction = 4;
            }
            if (cur.c < 0) {
                cur.c = Math.abs(cur.c);
                cur.direction = 3;
            }
        } else if (cur.direction == 4) {    // 왼
            cur.speed %= ((c - 1) * 2);
            cur.c -= cur.speed;

            if (cur.c < 0) {
                cur.c = Math.abs(cur.c);
                cur.direction = 3;
            }
            if (cur.c >= c) {
                cur.c = c - (cur.c - (c - 1) + 1);
                cur.direction = 4;
            }
        }

        // 이동한 상어의 위치를 tmp 에 기록 (크기가 큰 상어만)
        if (cur.z > tmp[cur.r][cur.c]) {
            tmp[cur.r][cur.c] = cur.z;
            sharks[cur.z] = new Shark(cur.r, cur.c, cur.speed, cur.direction, cur.z);
        }
    }

    // tmp 배열을 map 배열에 복사
    static void copyTmpToMap() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    // tmp 배열 0으로 초기화
    static void initTmpArray() {
        for (int[] t : tmp) {
            Arrays.fill(t, 0);
        }
    }

    static class Shark{
        int r, c, speed, direction, z;

        Shark(int r, int c, int speed, int direction, int z) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.direction = direction;
            this.z = z;
        }
    }
}
