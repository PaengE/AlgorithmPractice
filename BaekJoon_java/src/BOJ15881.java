import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ15881 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        String p = "pPAp";

        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(s);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        bw.write(String.valueOf(count));
        bw.close();
        br.close();
    }
}
