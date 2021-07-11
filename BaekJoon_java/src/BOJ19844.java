import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ19844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        bw.write(String.valueOf(solve(s)));
        bw.close();
        br.close();
    }

    static int solve(String s) {
        String[] str = s.split("[- ]");
        int cnt = str.length;

        Pattern p = Pattern.compile("^(c|j|n|m|t|s|l|l|d|qu|s)'[aeiouh]");
        for (int i = 0; i < str.length; i++) {
            Matcher m = p.matcher(str[i]);
            while (m.find()) {
                cnt++;
            }
        }

        return cnt;
    }
}
