import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] sort = arr.clone();
        Arrays.sort(sort);

        int idx = 0;
        for (int a : sort) {
            if (!map.containsKey(a)) {
                map.put(a, idx++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int a : arr) {
            sb.append(map.get(a) + " ");
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
