import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * no.10816 : 숫자 카드 2
 * title : 이분 탐색으로 값의 개수를 찾아 봅시다.
 * hint : 이진탐색 사용 -> 시간 초과 뜸
 */

public class BOJ10816_binary_search {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            int idx = binary_search(target, 0, n - 1);

            if (idx == -1) {
                bw.write("0 ");
            } else {
                int count = 1;
                int a = idx - 1, b = idx + 1;
                while(a >= 0 && arr[a] == target){
                    count++;
                    a--;
                }
                while (b <= n - 1 && arr[b] == target) {
                    count++;
                    b++;
                }
                bw.write(count + " ");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static int binary_search(long target, int left, int right) {
        int mid = (left + right) / 2;
        if (target == arr[mid]) {
            return mid;
        } else if (left != mid && target < arr[mid]) {
            return binary_search(target, left, mid - 1);
        } else if (mid != right && target > arr[mid]) {
            return binary_search(target, mid + 1, right);
        } else {
            return -1;
        }
    }
}
