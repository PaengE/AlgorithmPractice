import java.io.*;
import java.util.Arrays;

/**
 *  No.16639: 괄호 추가하기 3
 *  Hint: DP
 */

public class BOJ16639 {
    static int n;
    static int[][] min, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        min = new int[n][n];
        max = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(min[i], Integer.MAX_VALUE);
            Arrays.fill(max[i], Integer.MIN_VALUE);
        }

        char[] input = br.readLine().toCharArray();
        for (int i = 0; i < n; i += 2) {
            min[i][i] = input[i] - '0';
            max[i][i] = input[i] - '0';
        }

        // (i ~ ㅓ) 까지의 합은 (i ~ k) 의 합 + (k ~ j) 합을 이용
        for (int j = 2; j < n; j += 2) {
            for (int i = 0; i < n - j; i += 2) {
                for (int k = 2; k <= j; k += 2) {
                    char op = input[i + k - 1];
                    int[] tmp = new int[4];
                    tmp[0] = calculate(max[i][i + k - 2], max[i + k][i + j], op);
                    tmp[1] = calculate(min[i][i + k - 2], max[i + k][i + j], op);
                    tmp[2] = calculate(max[i][i + k - 2], min[i + k][i + j], op);
                    tmp[3] = calculate(min[i][i + k - 2], min[i + k][i + j], op);
                    Arrays.sort(tmp);
                    max[i][i + j] = Math.max(max[i][i + j], tmp[3]);
                    min[i][i + j] = Math.min(min[i][i + j], tmp[0]);
                }
            }
        }

        bw.write(String.valueOf(max[0][n - 1]));
        bw.close();
        br.close();
    }

    // 연산자 계산
    static int calculate(int n1, int n2, char op) {
        int res = 0;
        switch (op) {
            case '+':
                res = n1 + n2;
                break;
            case '-':
                res = n1 - n2;
                break;
            case '*':
                res = n1 * n2;
                break;
        }
        return res;
    }
}
