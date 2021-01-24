import java.io.*;
import java.util.ArrayList;

/**
 *  No.1644: 소수의 연속합
 *  Hint: 에라스토테네스의 체를 이용한 소수를 구한 후 수열을 만듦
 *        한 쪽에서 시작하는 투포인터를 사용하여 연속합 찾기
 */

public class BOJ1644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        boolean[] prime = isPrime(n);   // false면 소수 true면 소수아님

        if (n == 1) {
            bw.write("0");
            bw.close();
            br.close();
            return;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!prime[i]) {
                list.add(i);
            }
        }
        list.add(0);

        int start = 0, end = 0;
        int sum = list.get(0);
        int answer = 0;
        while (true) {
            if (start == end) {
                if (sum < n) {
                    sum += list.get(++end);
                } else if (sum > n) {
                    sum -= list.get(start++);
                    sum += list.get(++end);
                } else {
                    answer++;
                    start++;
                    end++;
                }
            } else {
                if (sum < n) {
                    sum += list.get(++end);
                } else if (sum > n) {
                    sum -= list.get(start++);
                } else {
                    answer++;
                    sum -= list.get(start++);
                }
            }
            if (start == list.size() - 1 || end == list.size() - 1) {
                break;
            }
        }
        bw.write(String.valueOf(answer));
        bw.close();
        br.close();
    }

    // 소수판별: false면 소수 true면 소수아님
    static boolean[] isPrime(int n) {
        boolean[] prime = new boolean[n + 1];
        prime[1] = true;

        // 2의 배수는 소수 아님
        for (int i = 4; i <= n; i += 2) {
            prime[i] = true;
        }
        // 3의 배수는 소수 아님
        for (int i = 6; i <= n; i += 3) {
            prime[i] = true;
        }

        for (int i = 5; i <= n; i += 2) {
            if (!prime[i]) {
                for (int j = 3; j <= Math.sqrt(i); j++) {
                    if (i % j == 0) {
                        prime[i] = true;
                        break;
                    }
                }
            }
            if (prime[i]) {
                for (int j = i; j <= n; j += i) {
                    prime[j] = true;
                }
            }
        }
        return prime;
    }
}
