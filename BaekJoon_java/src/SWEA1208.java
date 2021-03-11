import java.io.*;

import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1208 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 10; i++) {
            int dump = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[100];

            for (int j = 0; j < 100; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            for (int j = 0; j < dump; j++) {
                if (arr[99] - arr[0] == 1 || arr[99] - arr[0] == 0) {
                    break;
                } else {
                    arr[0]++;
                    arr[99]--;
                    Arrays.sort(arr);
                }
            }
            sb.append("#" + i + " " + (arr[99] - arr[0]) + "\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
