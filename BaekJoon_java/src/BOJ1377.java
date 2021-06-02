import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *  No.1377: 버블 소트
 *  Hint: 정렬 + Greedy
 */

public class BOJ1377 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Number> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new Number(Integer.parseInt(br.readLine()), i + 1));
        }

        Collections.sort(list, ((o1, o2) -> {
            if (o2.num == o1.num) {
                return o1.idx - o2.idx;
            }
            return o1.num - o2.num;
        }));

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, list.get(i - 1).idx - i);
        }

        bw.write(String.valueOf(max + 1));
        bw.close();
        br.close();

    }
    static class Number{
        int num, idx;

        Number(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
}
