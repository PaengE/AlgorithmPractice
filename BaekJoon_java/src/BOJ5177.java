import java.io.*;

public class BOJ5177 {
    static String s1, s2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            s1 = br.readLine().strip().toLowerCase();
            s2 = br.readLine().strip().toLowerCase();

            unify();

            if (s1.equals(s2)) {
                sb.append("Data Set " + i + ": equal\n\n");
            } else {
                sb.append("Data Set " + i + ": not equal\n\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void unify() {
        s1 = s1.replaceAll("[\\[\\{]", "(");
        s1 = s1.replaceAll("[\\]\\}]", ")");
        s2 = s2.replaceAll("[\\[\\{]", "(");
        s2 = s2.replaceAll("[\\]\\}]", ")");

        s1 = s1.replaceAll(",", ";");
        s2 = s2.replaceAll(",", ";");

        s1 = s1.replaceAll("(\\s)\\1+", "$1");
        s2 = s2.replaceAll("(\\s)\\1+", "$1");

        s1 = s1.replaceAll("\\s*(\\(|\\)|\\[|\\]|\\{|\\}|\\.|\\,|\\;|\\:)\\s*", "$1");
        s2 = s2.replaceAll("\\s*(\\(|\\)|\\[|\\]|\\{|\\}|\\.|\\,|\\;|\\:)\\s*", "$1");
    }
}
