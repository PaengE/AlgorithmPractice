import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ2954 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();

        Pattern pattern = Pattern.compile("[aeiou]{1}p[aeiou]");
        Matcher matcher = pattern.matcher(str);

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (matcher.find()) {
            sb.append(str, idx, matcher.start() + 1);
            idx = matcher.end();
        }
        sb.append(str, idx, str.length());

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}