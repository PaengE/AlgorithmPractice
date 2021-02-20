import java.io.*;

public class BOJ1259 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        while (true) {
            String s = br.readLine();
            if (s.equals("0")) {
                break;
            }

            String reverseS = new StringBuilder(s).reverse().toString();

            if (s.equals(reverseS)) {
                sb.append("yes\n");
            } else {
                sb.append("no\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
