import java.io.*;

public class BOJ10491 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s;

        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {
            if (s.toLowerCase().contains("problem")) {
                sb.append("yes\n");
            } else {
                sb.append("no\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
