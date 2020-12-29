import java.io.*;
import java.util.StringTokenizer;

public class BOJ1517 {
    static int[] arr, res;
    static long count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        res = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        merge(0, n - 1);

//        for (int i = 0; i < n; i++) {
//            System.out.print(res[i] + " ");
//        }
//        System.out.println();

        bw.write(String.valueOf(count));
        bw.close();
        br.close();

    }

    static void merge(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Divide
            merge(left, mid);
            merge(mid + 1, right);
            // Conquer
            mergeSort(left, right);
        }
    }

    static void mergeSort(int left, int right) {
        int mid = (left + right) / 2;
        int i = left;
        int j = mid + 1;
        int k = left;

        // 정렬
        while (i <= mid && j <= right) {
            if (arr[i] > arr[j]) {
                res[k++] = arr[j++];
                count += (mid - i) + 1;
            } else {
                res[k++] = arr[i++];
            }
        }


        if (i > mid) {  // 오른쪽 배열이 남은 경우 뒤에 이어서 붙임
            while (j <= right) {
                res[k++] = arr[j++];
            }
        } else {    // 왼쪽 배열이 남은경우 뒤에 이어서 붙임
            while (i <= mid) {
                res[k++] = arr[i++];
            }
        }

        // 정렬된 배열 update
        for (int l = left; l <= right; l++) {
            arr[l] = res[l];
        }
    }
}
