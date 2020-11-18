import java.io.*;
import java.util.StringTokenizer;

/**
 * no.10816 : 숫자 카드 2
 * title : 이분 탐색으로 값의 개수를 찾아 봅시다.
 * hint : 단순 배열 사용 -> 메모리 초과 뜸
 */

public class BOJ10816_array {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[200000001];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[Integer.parseInt(st.nextToken()) + 10000000] += 1;
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            bw.write(arr[Integer.parseInt(st.nextToken()) + 10000000] + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
