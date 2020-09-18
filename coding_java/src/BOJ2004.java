import java.io.*;
import java.util.StringTokenizer;
/*
    no.2004 : 조합 0의 개수
    hint : min(소인수2의개수, 소인수5의개수)
           1부터 n까지 모든 수를 검사하는 건 비효율적.
           나눌 수 있는 값만 검사
 */

public class BOJ2004 {
    private static long count_two(long n){
        long count2 = 0;
        for (long i = 2; i <= n; i *= 2) {
            count2 += (n / i);
        }
        return count2;
    }
    private static long count_five(long n){
        long count5 = 0;
        for (long i = 5; i <= n; i *= 5) {
            count5 += (n / i);
        }
        return count5;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        long count2 = count_two(n) - count_two(m) - count_two(n - m);
        long count5 = count_five(n) - count_five(m) - count_five(n - m);;

        bw.write(Math.min(count2, count5) + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
