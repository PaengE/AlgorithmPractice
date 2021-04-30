import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  No.1339: 단어 수학
 *  URL: https://www.acmicpc.net/problem/1339
 *  Hint: 순열
 */

public class BOJ1339 {
    static int ans;
    static String[] s;
    static HashMap<Character, Integer> map = new HashMap<>();
    static int[] res;
    static ArrayList<Integer> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        AtomicInteger idx = new AtomicInteger(0);
        s = new String[n];

        // 사용된 알파벳 indexing
        for (int i = 0; i < n; i++) {
            s[i] = br.readLine();
            for (int j = 0; j < s[i].length(); j++) {
                map.computeIfAbsent(s[i].charAt(j), k -> idx.getAndIncrement());
            }
        }

        res = new int[10];
        for (int i = 0; i < map.size(); i++) {
            arr.add(9 - i);
        }

        makePermutation(0);

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    // 만든 수열로 주어진 알파벳 수 계산
    static void calculation(int[] res) {
        int sum = 0;
        for (int i = 0; i < s.length; i++) {
            int tmp = 0;
            for (int j = 0; j < s[i].length(); j++) {
                tmp *= 10;
                tmp += res[map.get(s[i].charAt(j))];
            }
            sum += tmp;
        }

        // 최댓값 갱신
        if (ans < sum) {
            ans = sum;
        }
    }

    // 순열 만들기
    static void makePermutation(int depth) {
        if (depth == map.size()) {
            calculation(res);
            return;
        }
        
        for (int i = 0; i < map.size() - depth; i++) {
            res[depth] = arr.remove(i);
            makePermutation(depth + 1);
            arr.add(i, res[depth]);
        }
    }
}
