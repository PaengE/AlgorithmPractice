import java.io.*;
import java.util.Arrays;

/**
 *  No.1744: 수 묶기
 *  Hint: Greedy + sort
 */

public class BOJ1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        int minusCount = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (arr[i] <= 0) {
                minusCount++;
            }
        }
        Arrays.sort(arr);

        int ans = 0;
        // 음수 부분(0포함) - 두 개씩 묶어서 더하기
        for (int i = 1; i < minusCount; i += 2) {
            ans += (arr[i - 1] * arr[i]);
        }

        // 음수(0포함)가 홀수 개일 경우 그냥 더하기
        if (minusCount % 2 == 1) {
            ans += arr[minusCount - 1];
        }

        // 양수가 홀수 개일 경우 그냥 더하기
        if ((n - minusCount) % 2 == 1) {
            ans += arr[minusCount];
        }

        // 양수 부분 - 두 개씩 묶어서 더하기
        for (int i = n - 1; i > minusCount ; i -= 2) {
            int mul = arr[i] * arr[i - 1];
            int sum = arr[i] + arr[i - 1];
            if (mul > sum) {
                ans += mul;
            } else {
                ans += sum;
            }
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }
}
