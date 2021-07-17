import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ2929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Pattern p = Pattern.compile("[A-Z]");
        String s = br.readLine();
        Matcher m = p.matcher(s);

        int nopCnt = 0;
        while (m.find()) {
            if (m.start() == 0) {
                continue;
            }

            int curIdx = m.start() + nopCnt;
            int mod = curIdx % 4;
            if (mod != 0) {
                nopCnt += 4 - mod;
            }
        }

        bw.write(String.valueOf(nopCnt));
        br.close();
        bw.close();
    }
}
