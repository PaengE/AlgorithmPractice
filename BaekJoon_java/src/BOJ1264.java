import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ1264 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s;
        Pattern pattern = Pattern.compile("[aeiou]");

        StringBuilder sb = new StringBuilder();
        while (!(s = br.readLine().toLowerCase()).equals("#")) {
            Matcher matcher = pattern.matcher(s);

            int cnt = 0;
            while (matcher.find()) {
                cnt++;
            }
            sb.append(cnt + "\n");
        }

        bw.write(sb.substring(0, sb.length() - 1));
        bw.close();
        br.close();
    }
}
