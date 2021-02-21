import java.io.*;
import java.util.StringTokenizer;

public class ICPC2021_SinchonC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] cnt = new int[100001];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int left = 0;
        for (int i = 0; i < n; i++) {
            cnt[arr[i]]++;
            if (cnt[arr[i]] > k) {
                ans = Math.max(ans, i - left);
                while (cnt[arr[i]] > k) {
                    cnt[arr[left++]]--;
                }
            }
        }
        ans = Math.max(ans, n - left);

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }
}
