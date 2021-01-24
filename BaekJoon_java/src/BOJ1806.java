import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.1806: 부분합
 *  hint: 한쪽에서 시작하는 투 포인터 연습문제
 */

public class BOJ1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0;
        int answer = 0;
        int sum = arr[0];
        while (true) {
            if (start == end) {
                if (sum >= s) {
                    answer = 1;
                    break;
                } else {
                    sum += arr[++end];
                }
            } else {
                if (sum >= s) {
                    answer = answer == 0 ? end - start + 1 : Math.min(answer, end - start + 1);
                    while (sum - arr[start] >= s && start <= end) {
                        sum -= arr[start];
                        start++;
                        answer = Math.min(answer, end - start + 1);
                    }
                }
                sum += arr[++end];
            }

            if (start == arr.length - 1 || end == arr.length - 1) {
                break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.close();
        br.close();
    }
}
