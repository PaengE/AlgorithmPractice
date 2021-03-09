import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.17144: 미세먼지 안녕!
 *  URL: https://www.acmicpc.net/problem/17144
 *  Hint: 구현 문제
 */

public class BOJ17144 {
    static int r, c, t;
    static int[][] room, copy;
    static int[] purifierR;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        room = new int[r][c];
        copy = new int[r][c];
        purifierR = new int[2];


        int idx = 0;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    purifierR[idx++] = i;
                }
            }
        }

        for (int i = 0; i < t; i++) {
            diffusion();
            rotateUpper();
            rotateLower();
        }

        bw.write(String.valueOf(calcAnswer()));
        bw.close();
        br.close();
    }

    // 확산
    static void diffusion() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (room[i][j] == -1) { // 공기청정기면 pass
                    continue;
                }

                int cnt = 0;
                int amount = room[i][j] / 5;
                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];

                    if (canPossible(nr, nc)) {
                        cnt++;
                        copy[nr][nc] += amount;
                    }
                }
                copy[i][j] += room[i][j] - amount * cnt;
            }
        }
        copyArray();
    }

    // room 배열을 copy 배열로 초기화, copy 배열은 0으로 초기화
    static void copyArray() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                room[i][j] = copy[i][j];
                copy[i][j] = 0;
            }
        }
    }

    // 확산 가능한 지 판별
    static boolean canPossible(int cr, int cc) {
        if (cr >= 0 && cr < r && cc >= 0 && cc < c && room[cr][cc] != -1) {
            return true;
        } else {
            return false;
        }
    }

    static int calcAnswer() {
        int ans = 0;
        for (int i = 0; i < r; i++) {
            ans += Arrays.stream(room[i]).sum();
        }
        return ans + 2;
    }

    static void rotateUpper() {
        // down
        for (int i = purifierR[0]; i > 0; i--) {
            room[i][0] = room[i - 1][0];
        }
        // left
        for (int i = 0; i < c - 1; i++) {
            room[0][i] = room[0][i + 1];
        }
        // up
        for (int i = 0; i < purifierR[0]; i++) {
            room[i][c - 1] = room[i + 1][c - 1];
        }
        // right
        for (int i = c - 1; i > 0; i--) {
            room[purifierR[0]][i] = room[purifierR[0]][i - 1];
        }
        room[purifierR[0]][0] = -1;
        room[purifierR[0]][1] = 0;
    }
    static void rotateLower() {
        // up
        for (int i = purifierR[1]; i < r - 1; i++) {
            room[i][0] = room[i + 1][0];
        }
        // left
        for (int i = 0; i < c - 1; i++) {
            room[r - 1][i] = room[r - 1][i + 1];
        }
        // down
        for (int i = r - 1; i > purifierR[1]; i--) {
            room[i][c - 1] = room[i - 1][c - 1];
        }
        // right
        for (int i = c - 1; i > 0; i--) {
            room[purifierR[1]][i] = room[purifierR[1]][i - 1];
        }
        room[purifierR[1]][0] = -1;
        room[purifierR[1]][1] = 0;
    }

    static void print() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(room[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
