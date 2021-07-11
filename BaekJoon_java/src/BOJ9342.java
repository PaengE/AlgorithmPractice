import java.io.*;
import java.util.regex.Pattern;

public class BOJ9342 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        Pattern p = Pattern.compile("^[ABCDEF]?A+F+C+[ABCDEF]?$");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if (p.matcher(s).matches()) {
                sb.append("Infected!\n");
            } else {
                sb.append("Good\n");
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
