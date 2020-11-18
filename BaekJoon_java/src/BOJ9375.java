import java.io.*;
/*
    no.9375 : 패션왕 신해빈
    hint : (종류1의 개수 + 1) * (종류2의 개수 + 1) * ... * (종류n의 개수 +1) - 1
 */

public class BOJ9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(br.readLine());

            if (n == 0){
                bw.write("0\n");
            } else {
                int[] arr = new int[31];
                String[] str = new String[31];
                int idx = 0;

                for(int j = 0; j < n; j++) {
                    String s = br.readLine().split(" ")[1];
                    int k = 0;
                    for (k = 0; k <= idx; k++) {
                        if (s.equals(str[k])) {
                            arr[k] += 1;
                            break;
                        }
                    }
                    if (k > idx) {
                        str[idx] = s;
                        arr[idx] = 1;
                        idx++;
                    }
                }
                int res = 1;
                for(int j = 0; j < idx; j++){
                    res *= (arr[j] + 1);
                }
                bw.write((res-1) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
