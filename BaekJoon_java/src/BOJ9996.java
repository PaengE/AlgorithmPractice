import java.io.*;

public class BOJ9996 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String pattern = br.readLine();
        String[] splits = pattern.split("\\*");

        String p = "^" + splits[0] + "[a-z]*" + splits[1] + "$";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if (s.matches(p)) {
                sb.append("DA\n");
            } else {
                sb.append("NE\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
