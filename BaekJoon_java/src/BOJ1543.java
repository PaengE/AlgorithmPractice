import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ1543 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String t = br.readLine();
        String s = br.readLine();

        Pattern p = Pattern.compile(s);
        Matcher m = p.matcher(t);

        int cnt = 0;
        while (m.find()) {
            cnt++;
        }

        bw.write(String.valueOf(cnt));
        bw.close();
        br.close();
    }
}
