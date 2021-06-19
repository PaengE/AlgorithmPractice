import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.12931: 두 배 더하기
 *  Hint: Greedy + bitCount
 */

public class BOJ12931 {
    static int ans, maxMultiply;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            findAnswer(Integer.parseInt(st.nextToken()));
        }
        ans += maxMultiply;

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static void findAnswer(int num) {
        String binaryString = Integer.toBinaryString(num);
        int bitCount = Integer.bitCount(num);
        maxMultiply = Math.max(maxMultiply, binaryString.length() - 1);
        ans += bitCount;
    }
}
