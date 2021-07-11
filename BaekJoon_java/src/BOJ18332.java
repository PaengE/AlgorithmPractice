import java.io.*;
import java.util.HashSet;

public class BOJ18332 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("@");
            if (str.length > 2) {
                continue;
            }

            String s1 = processingPeriods(str[0]).replaceAll("\\.", "");
            if (s1.contains("-") || s1.length() < 6 || s1.length() > 30) {
                continue;
            }

            String s2 = processingPeriods(str[1]);
            if (s2.contains("_") || s2.length() < 3 || s2.length() > 30) {
                continue;
            }
            set.add(s1 + "@" + s2);
        }
        bw.write(String.valueOf(set.size()));
        br.close();
        bw.close();
    }

    static String processingPeriods(String s) {
        if (s.charAt(0) == '.' || s.charAt(s.length() - 1) == '.') {
            return "";
        }

        if (s.contains("..")) {
            return "";
        }

        return s.toLowerCase();
    }
}
