import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 시간초과.,.
public class ICPC2021_SinchonB {
    static boolean flag = false;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            arr.add(i);
        }

        permutation(arr, res, n, n, k);

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void permutation(ArrayList<Integer> arr, ArrayList<Integer> res, int n, int r, int k) {
        if (r == 0) {
            int size = res.size();
            int cnt = 0;
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (res.get(i) > res.get(j)) {
                        cnt++;
                    }
                }
            }

            if (cnt == k) {
                flag = true;
                res.forEach(a -> sb.append(a + " "));
            }
        }


        for (int i = 0; i < n; i++) {
            if (flag) {
                return;
            }
            res.add(arr.remove(i));
            permutation(arr, res, n - 1, r - 1, k);
            arr.add(i, res.remove(res.size() - 1));
        }
    }
}
