import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.16986: 인싸들의 가위바위보
 *  Hint: BruteForce + 구현
 */

public class BOJ16986 {
    static int n, k;
    static int[][] a;
    static int[][] commands;
    static boolean[] used;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        used = new boolean[n + 1];  // 순열 생성에 필요한 배열

        a = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        commands = new int[4][21];
        for (int i = 2; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 20; j++) {
                commands[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        permutation(1);

        bw.write(flag ? "1" : "0");
        bw.close();
        br.close();
    }

    // 1번 플레이어가 이기면 true, 그러지 못하면 false 리턴
    static boolean gameStart() {
        int[] winCnt = new int[4];
        int[] actionIndex = new int[4];
        Arrays.fill(actionIndex, 1);

        int player1 = 1, player2 = 2, nextPlayer = 3;

        while (true) {
            nextPlayer = 6 - player1 - player2;
            if (winCnt[1] == k) {
                return true;
            }

            if (winCnt[2] == k || winCnt[3] == k) {
                return false;
            }

            if (actionIndex[1] == n + 1 || actionIndex[2] == 21 || actionIndex[3] == 21) {
                return false;
            }

            int winner = whoWin(player1, player2, actionIndex);
            winCnt[winner]++;
            actionIndex[player1]++;
            actionIndex[player2]++;

            player1 = winner;
            player2 = nextPlayer;
        }
    }

    // 순열
    static void permutation(int depth) {
        if (flag) { // 정답을 찾았으면 리턴
            return;
        }

        if (depth == n + 1) {
            if (gameStart()) {
                flag = true;    // 정답 찾음 표시
            }
            return;
        }

        // 1번 플레이어의 action 구성
        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                used[i] = true;
                commands[1][depth] = i;
                permutation(depth + 1);
                used[i] = false;
            }
        }
    }

    // 이긴 사람의 번호를 리턴
    static int whoWin(int p1, int p2, int[] actionIndex) {
        int action1 = commands[p1][actionIndex[p1]];
        int action2 = commands[p2][actionIndex[p2]];

        if (a[action1][action2] == 2) {
            return p1;
        } else if (a[action1][action2] == 1) {
            return Math.max(p1, p2);
        } else {
            return p2;
        }
    }
}
