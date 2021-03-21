import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 *  No.2448: 별 찍기 - 11
 *  URL: https://www.acmicpc.net/problem/2448
 *  Hint: 비교적 간단한 규칙
 */

public class BOJ2448 {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = sc.nextInt();
        map = new char[n][2 * n - 1];

        for (char[] t : map) {
            Arrays.fill(t, ' ');
        }

        drawing(0, n - 1, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        sc.close();
    }

    static void drawing(int topX, int topY, int n) {
        if (n == 3) {
            map[topX][topY] = '*';
            map[topX + 1][topY - 1] = map[topX + 1][topY + 1] = '*';
            map[topX + 2][topY - 2] = map[topX + 2][topY - 1] = map[topX + 2][topY] = map[topX + 2][topY + 1] = map[topX + 2][topY + 2] = '*';
            return;
        }

        drawing(topX, topY, n / 2);
        drawing(topX + n / 2, topY - n / 2, n / 2);
        drawing(topX + n / 2, topY + n / 2, n / 2);
    }
}
