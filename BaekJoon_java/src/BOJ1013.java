import java.io.*;

public class BOJ1013 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if (s.matches("^(100+1+|01)+$")) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
