import java.io.*;

public class BOJ3447 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s;

        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {

            while (s.contains("BUG")) {
                s = s.replaceAll("BUG", "");
            }

            sb.append(s + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
