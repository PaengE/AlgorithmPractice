import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.9527: 1의 개수 세기
 *  URL: https://www.acmicpc.net/problem/9527
 *  Hint: 비트연산 + 규칙 찾기
 */

public class BOJ9527 {
    static long[] bit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        initBitCount();

        long ans = getBitCount(b) - getBitCount(a - 1);
        bw.write(String.valueOf(ans));
        br.close();
        bw.close();
    }

    static long getBitCount(long x) {
        long ans = x & 1;

        for (int i = 54; i > 0; i--) {
            if ((x & (1L << i)) > 0L) { // 숫자 x의 i번째 비트가 1이면
                ans += bit[i - 1] + (x - (1L << i) + 1);
                System.out.println("bit[" + (i - 1) + "] = " + bit[i - 1] + ", t= " + (x - (1L << i) + 1));
                x -= (1L << i);
                System.out.println("x = " + x);
            }

        }
        return ans;
    }

    // i번째 비트가 1일 때의 비트카운트(누적)
    static void initBitCount() {
        bit = new long[55]; // 입력의 최댓값인 10^16의 비트길이는 54이므로 (0부터)
        bit[0] = 1;
        for (int i = 1; i < 55; i++) {
            bit[i] = 2 * bit[i - 1] + (1L << i);
            System.out.println("bit["+i+"] = " + bit[i]);
        }
    }
}
