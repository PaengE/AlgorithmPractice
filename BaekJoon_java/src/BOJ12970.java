import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.12970: AB
 *  Hint: Greedy
 */

public class BOJ12970 {
    static int n, k;
    static char[] ans;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ans = new char[n];

        if (k == 0) {   // k == 0이면 그냥 A로 다  채움
            flag = true;
            for (int i = 0; i < n; i++) {
                ans[i] = 'A';
            }
        } else {
            greedy();
        }

        StringBuilder sb = new StringBuilder();
        if (flag) {
            for (int i = 0; i < n; i++) {
                sb.append(ans[i]);
            }
            bw.write(sb.toString());
        } else {
            bw.write("-1");
        }

        bw.close();
        br.close();
    }

    static void greedy() {
        int b = n;
        int a = 0;

        while (a <= n) {
            // (A, B) 쌍을 만들 수 있는 최대 개수는 a * b개
            if (a * b < k) {
                a++;
                b--;
                continue;
            }

            // 문자열을 B로 모두 채움
            Arrays.fill(ans, 'B');

            // a * b <= k 인 a,b의 개수를 찾은 뒤 앞에서부터 a - 1개 만큼 A를 채움
            for (int i = 0; i < a - 1; i++) {
                ans[i] = 'A';
            }

            // 현재 상태에서 (A, B) 쌍의 개수는 (a - 1) * b 개 
            // 맨 오른쪽에 A를 하나 두고, 왼쪽으로 한 칸씩 이동할 때마다 만들 수 있는 (A, B) 쌍의 개수가 하나씩 늘어남.
            // 그래서 remain 만큼의 (A, B) 쌍이 필요하므로 맨 오른쪽에서 왼쪽 방향으로 remain 만큼 떨어진 곳을 A로 변경
            int res = (a - 1) * b;
            int remain = k - res;
            ans[(n - 1) - remain] = 'A';
            flag = true;
            break;
        }
    }
}
