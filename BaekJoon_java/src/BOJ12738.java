import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * no.12738 : 가장 긴 증가하는 부분 수열 3 (LIS)
 * hint : 아무 자료구조 사용하여도 무방 -> ArrayList 사용
 *        1. 리스트의 마지막 보다 크면 삽입.
 *        2. 리스트의 마지막 보다 작으면 리스트 이진탐색으로 적절한 위치에 삽입.
 */

public class BOJ12738 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.MIN_VALUE);

        int n = Integer.parseInt(br.readLine());
        int val = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            val = Integer.parseInt(st.nextToken());

            if (list.get(list.size() - 1) < val) {
                list.add(val);
            } else {
                int left = 1;
                int right = list.size() - 1;

                while (left < right) {
                    int mid = (left + right) / 2;
                    if (list.get(mid) < val){
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                list.set(right, val);
            }
        }

        bw.write((list.size() - 1) + "");
        bw.flush();
        br.close();
        bw.close();
    }
}