import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * no.1920 : 수 찾기
 * title : 배열을 정렬한 후 이분 탐색으로 값을 찾아 봅시다.
 * hint : sort + binary_search
 */

public class BOJ1920 {
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            long target = Long.parseLong(st.nextToken());
            boolean answer = binary_search(target, 0, n - 1);

            if (answer)
                bw.write("1\n");
            else
                bw.write("0\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean binary_search(long target, int left, int right) {
        int mid = (left + right) / 2;
        if (target == arr[mid]) {
            return true;
        } else if (left != mid && target < arr[mid]) {
            return binary_search(target, left, mid - 1);
        } else if (mid != right && target > arr[mid]) {
            return binary_search(target, mid + 1, right);
        } else {
            return false;
        }
    }
}
