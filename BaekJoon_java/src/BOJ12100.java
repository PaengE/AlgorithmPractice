import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.12100: 2048 (Easy)
 *  URL: https://www.acmicpc.net/problem/12100
 *  Hint: 단순 구현 + BruteForce
 */

public class BOJ12100 {
    static int[][] arr, copy;
    static int n, ans;
    static int[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        list = new int[5];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0);
        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }
    static void backtracking(int lv) {
        if (lv == 5) {
            copyArray();
            for (int i = 0; i < list.length; i++) {
                operation(list[i]);
            }

            ans = Math.max(ans, getMaxValue(copy));
            return;
        }

        for (int i = 0; i < 4; i++) {
            list[lv] = i;
            backtracking(lv + 1);
            list[lv] = 0;
        }
    }

    // 0:moveLeft, 1:moveRight, 2:moveUp, 3:moveDown
    static void operation(int num) {
        if (num == 0) {
            moveLeft();
        } else if (num == 1) {
            moveRight();
        } else if (num == 2) {
            moveUp();
        } else {
            moveDown();
        }
    }

    static void moveLeft() {
        // 각 행
        for (int row = 0; row < n; row++) {
            int idx = 0;    // 블록을 저장할 인덱스
            int blockSize = 0;  // 합칠 수 있는 블록의 크기(0이면 합칠 수 없는 상태)

            for (int col = 0; col < n; col++) {
                if (copy[row][col] != 0) {  // 현재 칸이 0이 아닐 때
                    if (blockSize == copy[row][col]) {  // 현재 칸과 합칠 블록이 같으면
                        // 합친 후, 블록 상태 조정 및 해당 칸 0으로 채움
                        copy[row][idx - 1] = blockSize * 2; 
                        blockSize = 0;
                        copy[row][col] = 0;
                    } else {    // 현재 칸과 합칠 블럭이 다르면
                        // 그냥 왼쪽으로만 붙이고, 합칠 수 있는 블럭의 크기를 업데이트함
                        blockSize = copy[row][col];
                        copy[row][col] = 0;
                        copy[row][idx++] = blockSize;
                    } 
                }
            }
        }
    }

    static void moveRight() {
        // 각 행
        for (int row = 0; row < n; row++) {
            int idx = n - 1;    // 블록을 저장할 인덱스
            int blockSize = 0;  // 합칠 수 있는 블록의 크기(0이면 합칠 수 없는 상태)

            for (int col = n - 1; col >= 0; col--) {
                if (copy[row][col] != 0) {  // 현재 칸이 0이 아닐 때
                    if (blockSize == copy[row][col]) {  // 현재 칸과 합칠 블록이 같으면
                        // 합친 후, 블록 상태 조정 및 해당 칸 0으로 채움
                        copy[row][idx + 1] = blockSize * 2;
                        blockSize = 0;
                        copy[row][col] = 0;
                    } else {    // 현재 칸과 합칠 블럭이 다르면
                        // 그냥 오른쪽으로만 붙이고, 합칠 수 있는 블럭의 크기를 업데이트함
                        blockSize = copy[row][col];
                        copy[row][col] = 0;
                        copy[row][idx--] = blockSize;
                    }
                }
            }
        }
    }

    static void moveUp() {
        // 각 열
        for (int col = 0; col < n; col++) {
            int idx = 0;    // 블록을 저장할 인덱스
            int blockSize = 0;  // 합칠 수 있는 블록의 크기(0이면 합칠 수 없는 상태)

            for (int row = 0; row < n; row++) {
                if (copy[row][col] != 0) {  // 현재 칸이 0이 아닐 때
                    if (blockSize == copy[row][col]) {  // 현재 칸과 합칠 블록이 같으면
                        // 합친 후, 블록 상태 조정 및 해당 칸 0으로 채움
                        copy[idx - 1][col] = blockSize * 2;
                        blockSize = 0;
                        copy[row][col] = 0;
                    } else {    // 현재 칸과 합칠 블럭이 다르면
                        // 그냥 위쪽으로만 붙이고, 합칠 수 있는 블럭의 크기를 업데이트함
                        blockSize = copy[row][col];
                        copy[row][col] = 0;
                        copy[idx++][col] = blockSize;
                    }
                }
            }
        }
    }

    static void moveDown() {
        // 각 열
        for (int col = 0; col < n; col++) {
            int idx = n - 1;    // 블록을 저장할 인덱스
            int blockSize = 0;  // 합칠 수 있는 블록의 크기(0이면 합칠 수 없는 상태)

            for (int row = n - 1; row >= 0; row--) {
                if (copy[row][col] != 0) {  // 현재 칸이 0이 아닐 때
                    if (blockSize == copy[row][col]) {  // 현재 칸과 합칠 블록이 같으면
                        // 합친 후, 블록 상태 조정 및 해당 칸 0으로 채움
                        copy[idx + 1][col] = blockSize * 2;
                        blockSize = 0;
                        copy[row][col] = 0;
                    } else {    // 현재 칸과 합칠 블럭이 다르면
                        // 그냥 아래쪽으로만 붙이고, 합칠 수 있는 블럭의 크기를 업데이트함
                        blockSize = copy[row][col];
                        copy[row][col] = 0;
                        copy[idx--][col] = blockSize;
                    }
                }
            }
        }
    }

    static int getMaxValue(int[][] arr) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, arr[i][j]);
            }
        }
        return max;
    }

    static void copyArray() {
        copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = arr[i][j];
            }
        }
    }
}
