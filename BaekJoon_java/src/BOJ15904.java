import java.io.*;

public class BOJ15904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();

        if (tryAbbreviation(s)) {
            bw.write("I love UCPC");
        } else {
            bw.write("I hate UCPC");
        }

        bw.close();
        br.close();
    }

    static boolean tryAbbreviation(String s) {
        return s.matches("\\V*U\\V*C\\V*P\\V*C\\V*");
    }
}
