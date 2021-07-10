import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ4659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str;

        StringBuilder sb = new StringBuilder();
        while (!(str = br.readLine()).equals("end")) {
            if (rule1(str) && rule2(str) && rule3(str)) {
                sb.append("<" + str + ">" + " is acceptable.\n");
            } else {
                sb.append("<" + str + ">" + " is not acceptable.\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static boolean rule1(String s) {
        return s.matches(".*[aeiou].*");
    }

    static boolean rule2(String s) {
        if (s.matches("(.*[aeiou]{3}.*)|.*[^aeiou]{3}.*")) {
            return false;
        }

        return true;
    }

    static boolean rule3(String s) {
        Pattern p = Pattern.compile("(\\w)\\1");
        Matcher m = p.matcher(s);

        while (m.find()) {
            if (!(m.group(1).equals("e") || m.group(1).equals("o"))) {
                return false;
            }
        }

        return true;
    }
}
