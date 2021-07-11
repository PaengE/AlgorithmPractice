import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();

        Pattern p = Pattern.compile("<\\V+?>");
        Matcher m = p.matcher(s);

        int idx = 0;
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            String[] str = s.substring(idx, m.start()).split(" ");
            sb.append(reverse(str));
            sb.append(m.group());
            idx = m.end();
        }

        String[] remain = s.substring(idx, s.length()).split(" ");
        sb.append(reverse(remain));
        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    static String reverse(String[] str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            sb.append(new StringBuilder(str[i]).reverse());
            if (i != str.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
