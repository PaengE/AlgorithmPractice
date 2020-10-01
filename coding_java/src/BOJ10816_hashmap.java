import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * no.10816 : 숫자 카드 2
 * title : 이분 탐색으로 값의 개수를 찾아 봅시다.
 * hint : HashMap 사용
 */

public class BOJ10816_hashmap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(st.nextToken());
            map.putIfAbsent(t, 0);
            map.replace(t, map.get(t) + 1);
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            int key = Integer.parseInt(st.nextToken());

            if (!map.containsKey(key)) {
                bw.write("0 ");
            } else {
                bw.write(map.get(key) + " ");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

